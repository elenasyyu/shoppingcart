package mc.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import mc.shoppingcart.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>,
	JpaSpecificationExecutor<Product> {
	public Product findFirstByName(String name);
}