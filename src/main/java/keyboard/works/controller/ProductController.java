package keyboard.works.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import keyboard.works.model.request.ProductRequest;
import keyboard.works.model.response.ProductResponse;
import keyboard.works.service.ProductService;

@RestController
@RequestMapping(
	path = "/products",
	produces = MediaType.APPLICATION_JSON_VALUE
)
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<ProductResponse> getProducts(ProductRequest productRequest) {
		return productService.getProducts(productRequest);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
		return productService.createProduct(productRequest);
	}
	
}
