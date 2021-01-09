package mc.shoppingcart.rest;

import java.util.ArrayList;

import javax.validation.Valid;

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

import mc.shoppingcart.dto.ShoppingCardDetailDto;

@RestController
@RequestMapping("/api/shoppingcart")
public class ShoppingCartApi {
    @GetMapping("")
    public ResponseEntity<Iterable<ShoppingCardDetailDto>> getAll() {
//        Iterable<Pet> pets = petService.getAllPets();
        return new ResponseEntity<>(new ArrayList<ShoppingCardDetailDto>(), HttpStatus.OK);
    }
    
    @GetMapping("/{cartname}")
    public ResponseEntity<ShoppingCardDetailDto> get(@PathVariable(name = "cartname") String cartname) {
//        Pet pet = petService.getPet(petId);
//        if (pet == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(new ShoppingCardDetailDto(), HttpStatus.OK);
    }
    
    @PostMapping("")
    public ResponseEntity<ShoppingCardDetailDto> create(@Valid @RequestBody ShoppingCardDetailDto cardDetailDto) {
    	return new ResponseEntity<>(new ShoppingCardDetailDto(), HttpStatus.OK);
    }
    
    @PutMapping("")
    public ResponseEntity<ShoppingCardDetailDto> update(@Valid @RequestBody ShoppingCardDetailDto cardDetailDto) {
    	return new ResponseEntity<>(new ShoppingCardDetailDto(), HttpStatus.OK);
    }
    
    @DeleteMapping("/{cartname}")
    public ResponseEntity<Void> deleteCart(@PathVariable(name = "cartname") String cartname) {
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping("/{cartname}/{itemName}")
    public ResponseEntity<ShoppingCardDetailDto> deleteItemFromCart(@PathVariable(name = "cartname") String cartname,
    		@PathVariable(name = "itemName") String itemName) {
    	return new ResponseEntity<>(new ShoppingCardDetailDto(), HttpStatus.OK);
    }
    
    @PostMapping("/{cartname}")
    public ResponseEntity<ShoppingCardDetailDto> checkout(@PathVariable(name = "cartname") String cartname) {
    	return new ResponseEntity<>(new ShoppingCardDetailDto(), HttpStatus.OK);
    }
}
