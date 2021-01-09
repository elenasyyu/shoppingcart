package mc.shoppingcart.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mc.shoppingcart.dto.ProductDto;
import mc.shoppingcart.entity.Product;
import mc.shoppingcart.exception.ExceptionInfo;
import mc.shoppingcart.exception.ShoppingCartException;
import mc.shoppingcart.service.IProductService;
import mc.shoppingcart.util.DtoConverter;

@RestController
@RequestMapping("/api/products")
public class ProductsApi {
	@Autowired
	IProductService productService;
	
    @GetMapping("")
    public ResponseEntity<Iterable<ProductDto>> getAll() {
    	List<Product> products = productService.getAllProducts();
    	
    	List<ProductDto> productsDto = new ArrayList<ProductDto>();
    	products.forEach(product -> {
    		productsDto.add(DtoConverter.convertProductToDto(product));
    	});
    	    	
    	return new ResponseEntity<>(productsDto, HttpStatus.OK);
    }
    
    @GetMapping("/{productname}")
    public ResponseEntity<ProductDto> get(@PathVariable(name = "productname") String productname)
    	throws ShoppingCartException {
    	try {
	    	Product product = productService.getProduct(productname);
	        return new ResponseEntity<>(DtoConverter.convertProductToDto(product), HttpStatus.OK);
    	} catch (Exception e) {
    		ShoppingCartException scException = new ShoppingCartException();
    		ExceptionInfo info = new ExceptionInfo("Product", "Error when get product by name " + productname);
    		scException.AddException(info);
    		
    		throw scException;
    	}
    }    
}