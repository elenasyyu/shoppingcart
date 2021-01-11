package mc.shoppingcart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import mc.shoppingcart.dto.ProductDto;
import mc.shoppingcart.dto.ShoppingCartDetailDto;
import mc.shoppingcart.dto.ShoppingCartItemDto;
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
    
    @Test
    public void shoppingCart() {
    	ShoppingCartDetailDto cartDetail = getCart("ononcart");
    	assertNull(cartDetail);
    	
    	createCart();
    	cartDetail = getCart("ononcart");
    	assertNotNull(cartDetail);
    	assertEquals("ononcart", cartDetail.getName());
    	
    	for (ShoppingCartItemDto item : cartDetail.getItems()) {
    		if (item.getItemName().equals("apple")) {
    			assertEquals(1.0, item.getItemPrice(), 0);
    		} else if (item.getItemName().equals("orange")) {
    			assertEquals(1.1, item.getItemPrice(), 0);
    		} else if (item.getItemName().equals("banana")) {
    			assertEquals(1.2, item.getItemPrice(), 0);
			} else if (item.getItemName().equals("pineapple")) {
				assertEquals(1.3, item.getItemPrice(), 0);
			} else if (item.getItemName().equals("pear")) {
				assertEquals(1.4, item.getItemPrice(), 0);
			} else if (item.getItemName().equals("cherry")) {
				assertEquals(1.5, item.getItemPrice(), 0);
			}
    	}
    }
    
    /////////////////////////////////////////////////////////
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
    
    private ShoppingCartDetailDto getCart(final String cartName){
    	List<ShoppingCartDetailDto> detailList = new ArrayList<ShoppingCartDetailDto>();
    	
    	Integer returnedCode = RestApiManager.sendRestApiCall(new CookieManager(), 
    			()->true, 
    			"http://localhost:8080/api/shoppingcart/" + cartName, 
    			HttpMethod.GET, 
    			(HttpURLConnection objConnection) -> {
    				objConnection.setRequestProperty("Content-Type", "application/json");
    			}, 
    			() -> null, 
    			(List<String> objResponse) -> generateCartDetail(objResponse, detailList),
    			(CookieManager objResponseCookie) -> {}
    	);  
    	
    	if (returnedCode == HttpURLConnection.HTTP_OK && detailList.size() > 0)
    		return detailList.get(0);
    	else
    		return null;
    }
    
    private static void generateCartDetail(final List<String> objResponse, List<ShoppingCartDetailDto> objDetailList) {
    	try {
    		for (String output : objResponse) {
    			ShoppingCartDetailDto objCartDetail = new ShoppingCartDetailDto();
    			
    			JSONObject objDetailJSON = new JSONObject(output);
    			
    			// name 
    			String name = objDetailJSON.getString("name");
    			objCartDetail.setName(name);
    			
    			// item
    			List<ShoppingCartItemDto> allItems = new ArrayList<ShoppingCartItemDto>();
    			JSONArray objItemsJSON = (JSONArray) objDetailJSON.get("items");
    			for (int i = 0; i < objItemsJSON.length(); i++) {
    				JSONObject objItemJSON = objItemsJSON.getJSONObject(i);
    				
    				// name
    				String itemName = objItemJSON.getString("itemName");
    				
    				// price
    				Double itemPrice = objItemJSON.getDouble("itemPrice");
    				
    				// quantity
    				Integer numOfItem = objItemJSON.getInt("numOfItem");
    				
    				ShoppingCartItemDto itemDto = new ShoppingCartItemDto();
    				itemDto.setItemName(itemName);
    				itemDto.setItemPrice(itemPrice);
    				itemDto.setNumOfItem(numOfItem);
    				
    				allItems.add(itemDto);
    			}
    			objCartDetail.setItems(allItems);
    			
    			objDetailList.add(objCartDetail);
    		}
    		
    	} catch (Exception e) {
    		
    	} finally {
    		
    	}
    }
    
    private boolean createCart() {
    	Integer returnedCode = RestApiManager.sendRestApiCall(new CookieManager(), 
    			()->true, 
    			"http://localhost:8080/api/shoppingcart", 
    			HttpMethod.POST, 
    			(HttpURLConnection objConnection) -> {
    				objConnection.setRequestProperty("Content-Type", "application/json");
    			}, 
    			() -> {
    				JSONObject objJSONBody = constructCreateCartRequestBody();
    				if (objJSONBody == null)
    					return null;
    				return objJSONBody.toString();
    			}, 
    			(List<String> objResponse) -> {}, 
    			(CookieManager objResponseCookie) -> {}
    	);  
    	
    	if (returnedCode == HttpURLConnection.HTTP_OK)
    		return true;
    	else
    		return false;
    }    
    
    private static final JSONObject constructCreateCartRequestBody() {
    	String BODY = "{\r\n"
    			+ "    \"name\": \"ononcart\",\r\n"
    			+ "    \"items\": [\r\n"
    			+ "        {\r\n"
    			+ "            \"itemName\": \"pineapple\",\r\n"
    			+ "            \"itemPrice\": 1.3,\r\n"
    			+ "            \"numOfItem\": 3\r\n"
    			+ "        },\r\n"
    			+ "        {\r\n"
    			+ "            \"itemName\": \"apple\",\r\n"
    			+ "            \"itemPrice\": 1.0,\r\n"
    			+ "            \"numOfItem\": 1\r\n"
    			+ "        },\r\n"
    			+ "        {\r\n"
    			+ "            \"itemName\": \"orange\",\r\n"
    			+ "            \"itemPrice\": 1.1,\r\n"
    			+ "            \"numOfItem\": 4\r\n"
    			+ "        }                         \r\n"
    			+ "    ]\r\n"
    			+ "}";
    	
    	JSONObject objCreateCartRequestBody = null;
    	try {
    		objCreateCartRequestBody = new JSONObject(BODY);
    	} catch (JSONException err) {
    		objCreateCartRequestBody = null;
    	}
    	
    	return objCreateCartRequestBody;
    	
    }
}
