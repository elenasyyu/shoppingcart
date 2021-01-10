package mc.shoppingcart;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertSame;

import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import mc.shoppingcart.dto.ProductDto;
import mc.shoppingcart.util.RestApiManager;

/**
 * Unit test for simple App.
 */
public class ShoppingCartTest 
{
    @Test
    public void getAllProducts() {
    	List<ProductDto> products = new ArrayList<ProductDto>();
    	
    	Integer returnedCode = RestApiManager.sendRestApiCall(new CookieManager(), 
    			()->true, 
    			"http://localhost:8080/api/products", 
    			HttpMethod.GET, 
    			(HttpURLConnection objConnection) -> {
    				objConnection.setRequestProperty("Content-Type", "application/json");
    			}, 
    			() -> null, 
    			(List<String> objResponse) -> generateProducts(objResponse, products), 
    			(CookieManager objResponseCookie) -> {}
    	);
    	
    	for (ProductDto product : products) {
    		if (product.getName().equals("apple")) {
    			assertEquals(1.0, product.getPrice(), 0);
    		} else if (product.getName().equals("orange")) {
    			assertEquals(1.1, product.getPrice(), 0);
    		} else if (product.getName().equals("banana")) {
    			assertEquals(1.2, product.getPrice(), 0);
			} else if (product.getName().equals("pineapple")) {
				assertEquals(1.3, product.getPrice(), 0);
			} else if (product.getName().equals("pear")) {
				assertEquals(1.4, product.getPrice(), 0);
			} else if (product.getName().equals("cherry")) {
				assertEquals(1.5, product.getPrice(), 0);
			}
    	}
    }
    
    private static void generateProducts(final List<String> objResponse, List<ProductDto> objProducts) {
    	// Example:
    	// [{"name":"apple","price":1.0},{"name":"orange","price":1.1},{"name":"banana","price":1.2},{"name":"pineapple","price":1.3},{"name":"pear","price":1.4},{"name":"cherry","price":1.5}]
    	try {
    		for (String output : objResponse) {
    			JSONArray objProductsJSON = new JSONArray(output);
    			for (int i = 0; i < objProductsJSON.length(); i++) {
    				JSONObject objProductJSON = objProductsJSON.getJSONObject(i);
    				
    				// Name
    				String name = objProductJSON.getString("name");
    				
    				// Prices
    				Double price = objProductJSON.getDouble("price");
    				
    				ProductDto product = new ProductDto();
    				product.setName(name);
    				product.setPrice(price);
    				
    				objProducts.add(product);
    			}
    		}
    	} catch (Exception e) {
    		
    	} finally {
    		
    	}
    }
}
