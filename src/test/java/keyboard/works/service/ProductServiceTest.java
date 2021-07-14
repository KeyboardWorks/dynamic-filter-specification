package keyboard.works.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import keyboard.works.model.request.ProductRequest;
import keyboard.works.model.response.ProductResponse;

@SpringBootTest
@Sql(scripts = {"/sql/delete-product.sql", "/sql/insert-product.sql"})
public class ProductServiceTest {

	@Autowired
	private ProductService productService;
	
	@Test
	public void getAllProductWithCodeSearch() {
		
		ProductRequest productRequest = new ProductRequest();
		productRequest.setCode("MB");
		
		List<ProductResponse> responses = productService.getProducts(productRequest);
		
		assertNotNull(responses);
		assertEquals(3, responses.size());
	}
	
	@Test
	public void getAllProductWithNameSearch() {
	
		ProductRequest productRequest = new ProductRequest();
		productRequest.setName("yamien");
		
		List<ProductResponse> responses = productService.getProducts(productRequest);
		
		assertNotNull(responses);
		assertEquals(2, responses.size());
	}
	
	@Test
	public void getAllProductWithCodeAndNameSearch() {
		
		ProductRequest productRequest = new ProductRequest();
		productRequest.setCode("mb");
		productRequest.setName("yamien");
		
		List<ProductResponse> responses = productService.getProducts(productRequest);
		
		assertNotNull(responses);
		assertEquals(2, responses.size());
	}
	
	@Test
	public void getAllProductNotFound() {
		ProductRequest productRequest = new ProductRequest();
		productRequest.setCode("kg");
		productRequest.setName("yamien");
		
		List<ProductResponse> responses = productService.getProducts(productRequest);
		
		assertNotNull(responses);
		assertEquals(0, responses.size());
	}
	
}
