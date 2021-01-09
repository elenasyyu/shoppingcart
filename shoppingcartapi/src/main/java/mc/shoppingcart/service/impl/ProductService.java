package mc.shoppingcart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import mc.shoppingcart.entity.Product;
import mc.shoppingcart.repository.ProductRepository;
import mc.shoppingcart.repositorySpecs.ProductSpecification;
import mc.shoppingcart.service.IProductService;

@Service
public class ProductService implements IProductService {
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProducts() {
		Specification<Product> spec = ProductSpecification.ByName(null);
		return productRepository.findAll(spec);
	}

	@Override
	public Product getProduct(String name) {
		Specification<Product> spec = ProductSpecification.ByName(name);
		return productRepository.findOne(spec).orElseThrow(() -> new IllegalArgumentException("Invalid product name " + name));
	}

}
