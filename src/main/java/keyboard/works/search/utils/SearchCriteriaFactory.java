package keyboard.works.search.utils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import keyboard.works.search.annotation.Searchable;
import keyboard.works.search.model.SearchCriteria;
import keyboard.works.search.model.SearchOperation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SearchCriteriaFactory<T> {
	
	private T requestSearchable;

	public List<SearchCriteria> createSearchCriterias() {
		
		Field[] fields = requestSearchable.getClass().getDeclaredFields();
		
		return List.of(fields).stream()
				.map(this::fieldToSearchCriteria)
				.filter(searchCriteria -> searchCriteria != null)
				.collect(Collectors.toList());
	}
	
	private SearchCriteria fieldToSearchCriteria(Field field) {
		
		field.setAccessible(true);
		Searchable searchable = field.getAnnotation(Searchable.class);
		
		if(Optional.ofNullable(searchable).isPresent())
		{
			Object value = null;
			
			try {
				value = field.get(requestSearchable);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			
			if(value != null)
				return createDefaultSearchCriteria(searchable, value);
			else if(searchable.nullable())
				return createNullableSearchCriteria(searchable);
		}
		
		return null;
	}
	
	private SearchCriteria createDefaultSearchCriteria(Searchable searchable, Object value) {
		return SearchCriteria.builder()
				.fields(searchable.field())
				.operation(searchable.operation())
				.value(value)
				.build();
	}
	
	private SearchCriteria createNullableSearchCriteria(Searchable searchable) {
		return SearchCriteria.builder()
				.fields(searchable.field())
				.operation(SearchOperation.NULLABLE)
				.build();
	}
	
}
