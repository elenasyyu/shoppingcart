import { Component, OnInit } from '@angular/core';
import { Cart } from '../../common/rest/models/cart'
import { CartItem } from '../../common/rest/models/cart-item'
import { ShoppingcartService } from '../../common/rest/services/shoppingcart.service'

import { ShoppingCartConstants } from './shoppingcart.constants'
import { ShoppingCartUtils } from './ShoppingCartUtils'

@Component({
  selector: 'app-shoppingcart',
  templateUrl: './shoppingcart.component.html',
  styleUrls: ['./shoppingcart.component.css']
})
export class ShoppingcartComponent implements OnInit {
  private cartItems: CartItem[];

  constructor(private shoppingcartService: ShoppingcartService) { }

  ngOnInit() {
    this.getCart(ShoppingCartConstants.CART_NAME);
  }

  getCart(cartname: string) {
    this.shoppingcartService.get(cartname).subscribe(
      cart => {
        if (this.isValidCart(cartname, cart.name)) {
            this.cartItems = cart.items;
        }
      }
    )
  }

  isValidCart(returnedCartName: string, expectedCartName: string) : boolean {
    if (!ShoppingCartUtils.isEmpty(returnedCartName) &&
      returnedCartName === expectedCartName) 
      return true;
    else
      return false; 
  }
}
