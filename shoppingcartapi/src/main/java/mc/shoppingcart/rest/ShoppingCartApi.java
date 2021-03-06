package mc.shoppingcart.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mc.shoppingcart.dto.ShoppingCartDetailDto;
import mc.shoppingcart.entity.Cart;
import mc.shoppingcart.exception.ExceptionInfo;
import mc.shoppingcart.exception.ShoppingCartException;
import mc.shoppingcart.service.ICartService;
import mc.shoppingcart.util.DtoConverter;

@RestController
@RequestMapping("/api/shoppingcart")
public class ShoppingCartApi {
	@Autowired
	ICartService cartService;

    @GetMapping("")
    public ResponseEntity<Iterable<ShoppingCartDetailDto>> getAll() {
    	List<Cart> carts = cartService.getAllCarts();
    	
		List<ShoppingCartDetailDto> cartsDto = StreamSupport
			.stream(carts.spliterator(), false)
			.map(DtoConverter::convertCartToDto)
			.collect(Collectors.toList()); 
    	    	
    	return new ResponseEntity<>(cartsDto, HttpStatus.OK);
    }
    
    @GetMapping("/{cartname}")
    public ResponseEntity<ShoppingCartDetailDto> get(@PathVariable(name = "cartname") String cartname) 
    	throws ShoppingCartException {
    	try {
	    	Cart cart = cartService.getCart(cartname);
	        return new ResponseEntity<>(DtoConverter.convertCartToDto(cart), HttpStatus.OK);
    	} catch (Exception e) {
    		ShoppingCartException scException = new ShoppingCartException();
    		ExceptionInfo info = new ExceptionInfo("Cart", "Error when get cart by name " + cartname);
    		scException.AddException(info);
    		
    		throw scException;
    	}
    }
    
    @PostMapping("")
    public ResponseEntity<Boolean> create(@Valid @RequestBody ShoppingCartDetailDto cardDetailDto)
    	throws ShoppingCartException {
    	try {
	    	Cart requestedCart = DtoConverter.convertCartFromDto(cardDetailDto);
	    	cartService.createCart(requestedCart);
	    	return new ResponseEntity<>(true, HttpStatus.OK);
    	} catch (Exception e) {
    		ShoppingCartException scException = new ShoppingCartException();
    		ExceptionInfo info = new ExceptionInfo("Cart", "Error when creating cart:  name " + cardDetailDto.getName());
    		scException.AddException(info);
    		
    		throw scException;    		
    	}
    }
    
    @PutMapping("")
    public ResponseEntity<Boolean> update(@Valid @RequestBody ShoppingCartDetailDto cardDetailDto) 
    	throws ShoppingCartException {
    	try {
	    	Cart requestedCart = DtoConverter.convertCartFromDto(cardDetailDto);
	    	cartService.updateCart(requestedCart);
	    	return new ResponseEntity<>(true, HttpStatus.OK);
    	} catch (Exception e) {
    		ShoppingCartException scException = new ShoppingCartException();
    		ExceptionInfo info = new ExceptionInfo("Cart", "Error when creating cart:  name " + cardDetailDto.getName());
    		scException.AddException(info);
    		
    		throw scException;    		
    	}
    }
    
    @DeleteMapping("/{cartname}")
    public ResponseEntity<Void> deleteCart(@PathVariable(name = "cartname") String cartname)
    	throws ShoppingCartException {
    	try {
	    	cartService.deleteCart(cartname);
	    	return new ResponseEntity<>(HttpStatus.OK);
    	} catch (Exception e) {
    		ShoppingCartException scException = new ShoppingCartException();
    		ExceptionInfo info = new ExceptionInfo("Cart", "Error when delete cart:  name " + cartname);
    		scException.AddException(info);
    		
    		throw scException;    		
    	}
    }
    
    @DeleteMapping("/{cartname}/{itemName}")
    public ResponseEntity<Boolean> deleteItemFromCart(@PathVariable(name = "cartname") String cartname,
    		@PathVariable(name = "itemName") String itemName) 
    	throws ShoppingCartException {
    	try {
    		List<String> productNames = new ArrayList<String>();
    		productNames.add(itemName);
    		cartService.deleteItemsFromCart(cartname, productNames);
	    	return new ResponseEntity<>(true, HttpStatus.OK);
    	} catch (Exception e) {
    		ShoppingCartException scException = new ShoppingCartException();
    		ExceptionInfo info = new ExceptionInfo("Cart", "Error when delete item " + itemName + " from cart " + cartname);
    		scException.AddException(info);
    		
    		throw scException;    		
    	}
    }
    
    @PostMapping("/checkout/{cartname}")
    public ResponseEntity<Boolean> checkout(@PathVariable(name = "cartname") String cartname) 
    	throws ShoppingCartException {
    	try {
    		cartService.checkoutCart(cartname);
	    	return new ResponseEntity<>(true, HttpStatus.OK);
    	} catch (Exception e) {
    		ShoppingCartException scException = new ShoppingCartException();
    		ExceptionInfo info = new ExceptionInfo("Cart", "Error when checkingout cart " + cartname);
    		scException.AddException(info);
    		
    		throw scException;    		
    	}
    }
}
