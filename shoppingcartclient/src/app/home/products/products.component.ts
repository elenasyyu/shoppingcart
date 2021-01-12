import { Component, OnInit, Input } from '@angular/core';
import { Product } from '../../common/rest/models/product';
import { ProductsService } from '../../common/rest/services/products.service'

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  private products: Product[];

  constructor(private productService: ProductsService) { }

  ngOnInit() {
    // console.log("aa")
    this.getProducts();
  }

  getProducts() {
    this.productService.getAll().subscribe(products => this.products = products);
  }
}
