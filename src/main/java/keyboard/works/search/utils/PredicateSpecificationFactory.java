package keyboard.works.search.utils;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import keyboard.works.search.model.SearchCriteria;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PredicateSpecificationFactory<T, X> {

	private Root<X> root; 
	
	private CriteriaBuilder criteriaBuilder;
	
	public List<Predicate> toPredicates(T requestSearchable) {
		
		return new SearchCriteriaFactory<T>(requestSearchable)
				.createSearchCriterias()
				.stream()
				.map(this::searchCriteriaToPredicate)
				.collect(Collectors.toList());
	}
	
	public Predicate toPredicate(T requestSearchable) {
	
		List<Predicate> predicates = toPredicates(requestSearchable);
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[] { }));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Predicate searchCriteriaToPredicate(SearchCriteria searchCriteria) {
		
		Path path = getLeafPath(searchCriteria);
		
		switch (searchCriteria.getOperation()) {
		case EQUAL:
			return criteriaBuilder.equal(path, searchCriteria.getValue());
		case LIKE:
			return criteriaBuilder.like(path, "%" + searchCriteria.getValue() + "%");
		case IN:
			return path.in((List) searchCriteria.getValue());
		case NULLABLE:
			return path.isNull();
		default: return null;
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	private Path getLeafPath(SearchCriteria searchCriteria) {
		
		Path path = root.get(searchCriteria.getFields()[0]);
		
		for(int i = 1; i < searchCriteria.getFields().length; i++)
			path = path.get(searchCriteria.getFields()[i]);
		
		return path;
	}
	
}
