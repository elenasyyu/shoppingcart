import { Component } from '@angular/core';
import { Product } from '../app/common/rest/models/product'
import { Cart } from '../app/common/rest/models/cart'
import { CartItem } from '../app/common/rest/models/cart-item'
import { ProductsService } from '../app/common/rest/services/products.service'
import { ShoppingcartService } from '../app/common/rest/services/shoppingcart.service'

import { ShoppingCartConstants } from './home/shoppingcart/shoppingcart.constants'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Welcome to Shopping Cart';

  itemName: string = null;
  itemQuantity: number = null;

  private products: Product[];
  private updateCart: Cart;

  constructor(private productService: ProductsService,
    private shoppingcartService: ShoppingcartService) { }

  ngOnInit() {
    // console.log("aa")
    this.getProducts();
  }

  getProducts() {
    this.productService.getAll().subscribe(products => this.products = products);
  }

  getProduct(productname: string) : Product {
    for (var product of  this.products) {
      if (product.name === productname) {
        return product;
      }
    }

    return null;
  }

  getProductPrice(productname: string): number {
    let product: Product = this.getProduct(productname);

    if (product != null)
      return product.price;
    else
      return 0;
  }

  onNameChange(event: any) {
    this.itemName = event.target.value;
    // console.log("little fish = " + this.itemName)
  }

  onQuantityChange(event: any) {
    this.itemQuantity = event.target.value;
    // console.log("little fish = " + this.itemQuantity)
  }

  createNewCartWithItem() {
    this.shoppingcartService.create(
      {
        name: ShoppingCartConstants.CART_NAME,
        items:[
          {
            itemName: this.itemName,
            itemPrice: this.getProductPrice(this.itemName),
            numOfItem:  this.itemQuantity
          }
        ]
      }
    )
    .toPromise()
    .then(data => {
    })
    .catch(err => {
    });
  }

  updateCartWithNewItem() {
    this.shoppingcartService.get(ShoppingCartConstants.CART_NAME).subscribe(cart => this.updateCart = cart);
    this.updateCart.items.push(
      {
        itemName: this.itemName,
        itemPrice: this.getProductPrice(this.itemName),        
        numOfItem:  this.itemQuantity
      }
    )
    console.log(this.updateCart.name)
    this.updateCart.items.forEach(item => console.log(item.itemName + ": Price = " + item.itemPrice + ", Quantity = " + item.numOfItem));

    this.shoppingcartService.update(this.updateCart)
    .toPromise()
    .then(data => {
    })
    .catch(err => {
    });
  }

  deleteItemFromCart() {
    this.shoppingcartService.deleteItemFromCart(
      {
        cartName: ShoppingCartConstants.CART_NAME,
        productName: this.itemName
      })
      .toPromise()
      .then(data => {
      })
      .catch(err => {
      });
  }
}
