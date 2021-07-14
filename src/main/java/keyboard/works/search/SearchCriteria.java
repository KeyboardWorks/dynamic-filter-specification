package keyboard.works.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@Builder
public class SearchCriteria {

	private String[] fields;
	private SearchOperation operation;
	private Object value;
}
