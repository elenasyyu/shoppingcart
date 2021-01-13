/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { Cart } from '../models/cart';

/**
 * Access to Shopping Cart
 */
@Injectable({
  providedIn: 'root',
})
class ShoppingcartService extends __BaseService {
  static readonly getPath = '/shoppingcart/{cart_name}';
  static readonly createPath = '/shoppingcart';
  static readonly updatePath = '/shoppingcart';
  static readonly deleteItemFromCartPath = '/shoppingcart/{cart_name}/{product_name}';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Get detail on certain shopping cart
   * @param cart_name Cart Name
   * @return OK
   */
  getResponse(cartName: string): __Observable<__StrictHttpResponse<Cart>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/shoppingcart/${encodeURIComponent(cartName)}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Cart>;
      })
    );
  }
  /**
   * Get detail on certain shopping cart
   * @param cart_name Cart Name
   * @return OK
   */
  get(cartName: string): __Observable<Cart> {
    return this.getResponse(cartName).pipe(
      __map(_r => _r.body as Cart)
    );
  }

  /**
   * Create a shopping cart with an item
   * @param cartdetail undefined
   * @return OK
   */
  createResponse(cartdetail?: Cart): __Observable<__StrictHttpResponse<boolean>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = cartdetail;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/shoppingcart`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'text'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return (_r as HttpResponse<any>).clone({ body: (_r as HttpResponse<any>).body === 'true' }) as __StrictHttpResponse<boolean>
      })
    );
  }
  /**
   * Create a shopping cart with an item
   * @param cartdetail undefined
   * @return OK
   */
  create(cartdetail?: Cart): __Observable<boolean> {
    return this.createResponse(cartdetail).pipe(
      __map(_r => _r.body as boolean)
    );
  }

  /**
   * Update Shopping Cart
   * @param cartdetail undefined
   * @return OK
   */
  updateResponse(cartdetail?: Cart): __Observable<__StrictHttpResponse<boolean>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = cartdetail;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/shoppingcart`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'text'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return (_r as HttpResponse<any>).clone({ body: (_r as HttpResponse<any>).body === 'true' }) as __StrictHttpResponse<boolean>
      })
    );
  }
  /**
   * Update Shopping Cart
   * @param cartdetail undefined
   * @return OK
   */
  update(cartdetail?: Cart): __Observable<boolean> {
    return this.updateResponse(cartdetail).pipe(
      __map(_r => _r.body as boolean)
    );
  }

  /**
   * delete item from Shopping Cart
   * @param params The `ShoppingcartService.DeleteItemFromCartParams` containing the following parameters:
   *
   * - `product_name`: Product Name
   *
   * - `cart_name`: Cart Name
   *
   * @return OK
   */
  deleteItemFromCartResponse(params: ShoppingcartService.DeleteItemFromCartParams): __Observable<__StrictHttpResponse<boolean>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;


    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/shoppingcart/${encodeURIComponent(params.cartName)}/${encodeURIComponent(params.productName)}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'text'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return (_r as HttpResponse<any>).clone({ body: (_r as HttpResponse<any>).body === 'true' }) as __StrictHttpResponse<boolean>
      })
    );
  }
  /**
   * delete item from Shopping Cart
   * @param params The `ShoppingcartService.DeleteItemFromCartParams` containing the following parameters:
   *
   * - `product_name`: Product Name
   *
   * - `cart_name`: Cart Name
   *
   * @return OK
   */
  deleteItemFromCart(params: ShoppingcartService.DeleteItemFromCartParams): __Observable<boolean> {
    return this.deleteItemFromCartResponse(params).pipe(
      __map(_r => _r.body as boolean)
    );
  }
}

module ShoppingcartService {

  /**
   * Parameters for deleteItemFromCart
   */
  export interface DeleteItemFromCartParams {

    /**
     * Product Name
     */
    productName: string;

    /**
     * Cart Name
     */
    cartName: string;
  }
}

export { ShoppingcartService }
