package mc.shoppingcart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import mc.shoppingcart.entity.Cart;
import mc.shoppingcart.repository.CartRepository;
import mc.shoppingcart.repositorySpecs.CartSpecification;
import mc.shoppingcart.service.ICartService;

@Service
public class CartService implements ICartService {
	@Autowired
	private CartRepository cartRepository;

	@Override
	public List<Cart> getAllCarts() {
		Specification<Cart> spec = CartSpecification.ByName(null);
		return cartRepository.findAll(spec);
	}

	@Override
	public Cart getCart(String name) {
		Specification<Cart> spec = CartSpecification.ByName(name);
		return cartRepository.findOne(spec).orElseThrow(() -> new IllegalArgumentException("Invalid cart name " + name));
	}
}
