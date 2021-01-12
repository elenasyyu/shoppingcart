/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { Product } from '../models/product';

/**
 * Access to Products
 */
@Injectable({
  providedIn: 'root',
})
class ProductsService extends __BaseService {
  static readonly getAllPath = '/products';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Get All Products
   * @return OK
   */
  getAllResponse(): __Observable<__StrictHttpResponse<Array<Product>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/products`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<Product>>;
      })
    );
  }
  /**
   * Get All Products
   * @return OK
   */
  getAll(): __Observable<Array<Product>> {
    return this.getAllResponse().pipe(
      __map(_r => _r.body as Array<Product>)
    );
  }
}

module ProductsService {
}

export { ProductsService }
