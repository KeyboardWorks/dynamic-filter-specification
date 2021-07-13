package keyboard.works.model.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

	private String code;
	
	private String name;
	
	private BigDecimal price;
	
}
