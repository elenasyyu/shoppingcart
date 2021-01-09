package mc.shoppingcart.rest;

import java.util.ArrayList;
import java.util.List;

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
    	
    	List<ShoppingCartDetailDto> cartsDto = new ArrayList<ShoppingCartDetailDto>();
    	carts.forEach(cart -> {
    		cartsDto.add(DtoConverter.convertCartToDto(cart));
    	});
    	    	
    	return new ResponseEntity<>(cartsDto, HttpStatus.OK);
    }
    
    @GetMapping("/{cartname}")
    public ResponseEntity<ShoppingCartDetailDto> get(@PathVariable(name = "cartname") String cartname) {
    	Cart cart = cartService.getCart(cartname);
        return new ResponseEntity<>(DtoConverter.convertCartToDto(cart), HttpStatus.OK);
    }
    
    @PostMapping("")
    public ResponseEntity<ShoppingCartDetailDto> create(@Valid @RequestBody ShoppingCartDetailDto cardDetailDto) {
    	return new ResponseEntity<>(new ShoppingCartDetailDto(), HttpStatus.OK);
    }
    
    @PutMapping("")
    public ResponseEntity<ShoppingCartDetailDto> update(@Valid @RequestBody ShoppingCartDetailDto cardDetailDto) {
    	return new ResponseEntity<>(new ShoppingCartDetailDto(), HttpStatus.OK);
    }
    
    @DeleteMapping("/{cartname}")
    public ResponseEntity<Void> deleteCart(@PathVariable(name = "cartname") String cartname) {
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping("/{cartname}/{itemName}")
    public ResponseEntity<ShoppingCartDetailDto> deleteItemFromCart(@PathVariable(name = "cartname") String cartname,
    		@PathVariable(name = "itemName") String itemName) {
    	return new ResponseEntity<>(new ShoppingCartDetailDto(), HttpStatus.OK);
    }
    
    @PostMapping("/{cartname}")
    public ResponseEntity<ShoppingCartDetailDto> checkout(@PathVariable(name = "cartname") String cartname) {
    	return new ResponseEntity<>(new ShoppingCartDetailDto(), HttpStatus.OK);
    }
}
