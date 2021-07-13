package keyboard.works.model.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

	private String code;
	
	private String name;
	
	private BigDecimal price;
	
}
