import { Component } from '@angular/core';
import { ProductsService } from '../app/common/rest/services/products.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Welcome to Shopping Cart';

  constructor(private productService: ProductsService) { }
}
