package mc.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import mc.shoppingcart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>,
	JpaSpecificationExecutor<Cart> {
	public Cart findFirstByName(String name);
}
