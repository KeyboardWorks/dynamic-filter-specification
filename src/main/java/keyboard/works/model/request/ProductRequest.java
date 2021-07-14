package keyboard.works.model.request;

import java.math.BigDecimal;

import keyboard.works.search.SearchOperation;
import keyboard.works.search.annotation.Searchable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

	@Searchable(field = "code", operation = SearchOperation.LIKE)
	private String code;
	
	@Searchable(field = "name", operation = SearchOperation.LIKE)
	private String name;
	
	private BigDecimal price;
	
}
