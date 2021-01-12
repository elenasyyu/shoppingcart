import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { ProductsComponent } from './home/products/products.component';
import { ShoppingcartComponent } from './home/shoppingcart/shoppingcart.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    ShoppingcartComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
