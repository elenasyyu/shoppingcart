# shoppingcart

A Shopping Cart Micro-Service is created in shoppingcartapi.  It will suppport the following functionality:
1. Get all products
2. Get specific product
3. Create Cart
4. Update Cart
5. Delete item from Cart
6. Delete Cart
7. Get Cart

## Table Structure
The following table are created to store the product and cart information
1. PRODUCT
2. CART
3. CART_ITEM

## Sending API
The following are the URL for sending API

### Get all products
Get http://localhost:8080/api/products

### Get specific product
```
Get http://localhost:8080/api/products/<product name>
For example: http://localhost:8080/api/products/apple
```