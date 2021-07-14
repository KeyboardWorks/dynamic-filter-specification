package keyboard.works.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import keyboard.works.entity.Product;
import keyboard.works.model.request.ProductRequest;
import keyboard.works.search.utils.PredicateSpecificationFactory;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductSpecification implements Specification<Product> {

	private static final long serialVersionUID = 6426987948276057914L;

	private ProductRequest productRequest;
	
	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		
		PredicateSpecificationFactory<ProductRequest, Product> predicateSpecificationFactory = 
				new PredicateSpecificationFactory<>(root, criteriaBuilder);
		
		return predicateSpecificationFactory.toPredicate(productRequest);
	}

}
