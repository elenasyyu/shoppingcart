package mc.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;

import mc.shoppingcart.entity.Cart;

public interface CartRepository extends CrudRepository<Cart, Integer> {
	public Cart findFirstByName(String name);
}
