# shoppingcart

A Shopping Cart Micro-Service is created in shoppingcartapi.  It will suppport the following functionality:
1. Get all products
2. Get specific product
3. Create Cart
4. Update Cart
5. Delete item from Cart
6. Delete Cart
7. Get All Carts
8. Get specific cart
9. Checkout cart

## Table Structure
H2 Database has been used in shoppingcardapi micro-service.  To access database, use the following link:  http://localhost:8080/h2-console.  
The following table are created to store the product and cart information
1. PRODUCT
2. CART
3. CART_ITEM

## Sending API
The following are the URL for sending API

### Get all products
Get http://localhost:8080/api/products

### Get specific product
Get http://localhost:8080/api/products/product_name
For example: http://localhost:8080/api/products/apple

### Create Cart
Post http://localhost:8080/api/shoppingcart with a body in json format

```
Example:
{
    "name": "ononcart",
    "items": [
        {
            "itemName": "pineapple",
            "itemPrice": 1.3,
            "numOfItem": 3
        },
        {
            "itemName": "apple",
            "itemPrice": 1.0,
            "numOfItem": 1
        },
        {
            "itemName": "orange",
            "itemPrice": 1.1,
            "numOfItem": 4
        }                         
    ]
}
```

### Update Cart
Put http://localhost:8080/api/shoppingcart with a body in json format. Will use the same json format in Create Cart

### Delete Item from Cart
DELETE http://localhost:8080/api/shoppingcart/cart_name/product_name
For example: http://localhost:8080/api/shoppingcart/ononcart/apple

### Delete Cart
DELETE http://localhost:8080/api/shoppingcart/cart_name
For example:  http://localhost:8080/api/shoppingcart/ononcart

### Get All Carts
Get http://localhost:8080/api/shoppingcart

### Get Specific Cart
Get http://localhost:8080/api/shoppingcart/cart_name
For example:  http://localhost:8080/api/shoppingcart/ononcart

### Checkout Cart
POST http://localhost:8080/api/shoppingcart/checkout/cart_name
For example:  http://localhost:8080/api/shoppingcart/checkout/ononcart
 
## Improve Item (in the future)
```
The following item is scheduled to be done in next phase
1. Currently the micro-service is just accessing the database directly.  Should add cache in the future to prevent go to database directly
```

# shoppingcartclient
## Create the project
This project is created using Angular CLI (https://github.com/angular/angular-cli)

## Create REST API Swagger File
The swagger json file is created using the following link:  https://editor.swagger.io/

## Create the RESP API
The REST API interface will be created by ng-swagger-gen (https://www.npmjs.com/package/ng-swagger-gen):

```
npm install ng-swagger-gen --save-dev
node_modules/.bin/ng-swagger-gen -i json_file_name -0 target_directory

For example:
"C:\gitproject\shoppingcart\shoppingcartclient\node_modules\.bin\ng-swagger-gen" -i "C:\gitproject\shoppingcart\shoppingcartclient\swagger_json\api.json" -o src/app/common/rest
```

## Create Component
Using the following command to create component

```
ng g c path
For example: ng g c /home/products
```

## To start the GUI
npm run start and then goto http://localhost:4200/

## Improve Item (in the future)
```
The following item is scheduled to be done in next phase
1. Need to refresh the page automatically for each create/update/delete action
```