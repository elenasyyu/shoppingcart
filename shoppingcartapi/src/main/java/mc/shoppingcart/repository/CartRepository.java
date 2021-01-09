package mc.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import mc.shoppingcart.entity.Cart;
import mc.shoppingcart.entity.Product;

public interface CartRepository extends JpaRepository<Product, Integer>,
	JpaSpecificationExecutor<Cart> {
	public Cart findFirstByName(String name);
}
