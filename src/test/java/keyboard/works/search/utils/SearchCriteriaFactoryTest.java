package keyboard.works.search.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import keyboard.works.search.SearchCriteria;
import keyboard.works.search.SearchOperation;
import keyboard.works.search.annotation.Searchable;
import lombok.Getter;
import lombok.Setter;

@SpringBootTest
public class SearchCriteriaFactoryTest {

	@Test
	public void createSearchCriteriasAllField() {
		
		TestRequest testRequest = new TestRequest();
		testRequest.setFieldEqual("Field Equal");
		testRequest.setFieldLike("Field Like");
		testRequest.setFieldIns(new LinkedList<>());
		testRequest.setFieldNullable("Field Nullable");
		
		List<SearchCriteria> searchCriterias = new SearchCriteriaFactory<TestRequest>(testRequest)
			.createSearchCriterias();
		
		assertNotNull(searchCriterias);
		assertEquals(4, searchCriterias.size());
		
		searchCriterias.forEach(searchCriteria -> {
			assertNotNull(searchCriteria.getFields());
			assertNotNull(searchCriteria.getOperation());
			assertNotNull(searchCriteria.getValue());
		});
	}
	
	@Test
	public void createSearchCriteriasAllNullField() {
		
		TestRequest2 testRequest = new TestRequest2();
		
		List<SearchCriteria> searchCriterias = new SearchCriteriaFactory<TestRequest2>(testRequest)
				.createSearchCriterias();
		
		assertNotNull(searchCriterias);
		assertEquals(0, searchCriterias.size());
	}
	
	@Test
	public void createSearchCriteriasNullableFieldNullValue() {
		
		TestRequest testRequest = new TestRequest();
		
		List<SearchCriteria> searchCriterias = new SearchCriteriaFactory<TestRequest>(testRequest)
				.createSearchCriterias();
		
		assertNotNull(searchCriterias);
		assertEquals(1, searchCriterias.size());
		assertEquals(SearchOperation.NULLABLE, searchCriterias.get(0).getOperation());
	}
	
	@Test
	public void createSearchCriteriasNullableFieldNotNullValue() {
		
		TestRequest testRequest = new TestRequest();
		testRequest.setFieldNullable("Field Nullable");
		
		List<SearchCriteria> searchCriterias = new SearchCriteriaFactory<TestRequest>(testRequest)
				.createSearchCriterias();
		
		assertNotNull(searchCriterias);
		assertEquals(1, searchCriterias.size());
		assertEquals(SearchOperation.EQUAL, searchCriterias.get(0).getOperation());
	}
	
	@Getter
	@Setter
	public static class TestRequest {
		
		@Searchable(field = {"field", "equal"}, operation = SearchOperation.EQUAL)
		private String fieldEqual;
		
		@Searchable(field = {"field", "like"}, operation = SearchOperation.LIKE)
		private String fieldLike;
		
		@Searchable(field = {"field", "in"}, operation = SearchOperation.IN)
		private List<String> fieldIns;
		
		@Searchable(field = {"field", "nullable"}, operation = SearchOperation.EQUAL, nullable = true)
		private String fieldNullable;
		
	}
	
	@Getter
	@Setter
	public static class TestRequest2 {
		
		@Searchable(field = {"field", "equal"}, operation = SearchOperation.EQUAL)
		private String fieldEqual;
		
		@Searchable(field = {"field", "like"}, operation = SearchOperation.LIKE)
		private String fieldLike;
		
		@Searchable(field = {"field", "in"}, operation = SearchOperation.IN)
		private List<String> fieldIns;
		
	}
	
}
