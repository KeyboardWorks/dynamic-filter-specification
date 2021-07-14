package keyboard.works.service;

import java.util.List;

import keyboard.works.model.request.ProductRequest;
import keyboard.works.model.response.ProductResponse;

public interface ProductService {

	List<ProductResponse> getProducts(ProductRequest productRequest);
	
	ProductResponse createProduct(ProductRequest productRequest);
	
}
