package keyboard.works.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import keyboard.works.entity.Product;
import keyboard.works.model.request.ProductRequest;
import keyboard.works.model.response.ProductResponse;
import keyboard.works.repository.ProductRepository;
import keyboard.works.repository.specification.ProductSpecification;
import keyboard.works.service.ProductService;
import keyboard.works.utils.ResponseHelper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<ProductResponse> getProducts(ProductRequest productRequest) {
		
		ProductSpecification specification = new ProductSpecification(productRequest);
		
		return ResponseHelper.createResponses(ProductResponse.class, productRepository.findAll(specification));
	}

	@Override
	public ProductResponse createProduct(ProductRequest productRequest) {
		
		Product product = new Product();
		BeanUtils.copyProperties(productRequest, product);
		
		product = productRepository.save(product);
		
		return ResponseHelper.createResponse(ProductResponse.class, product);
	}

}
