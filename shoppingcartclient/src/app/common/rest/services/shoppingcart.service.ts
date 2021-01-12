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
}

module ShoppingcartService {
}

export { ShoppingcartService }
