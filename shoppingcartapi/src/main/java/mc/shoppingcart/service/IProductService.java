package mc.shoppingcart.service;

import java.util.List;

import mc.shoppingcart.entity.Product;

public interface IProductService {
	public List<Product> getAllProducts();
	public Product getProduct(String name);
}