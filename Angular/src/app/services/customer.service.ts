import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, Observable} from "rxjs";
import {Customer} from "../models/customer/customer.module";
import {environment} from "../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) {
  }

  public getCustomers(): Observable<Array<Customer>> {
    return this.http.get<Array<Customer>>(environment.backendHost + "customers");
  }

  public getSearchCustomers(kw: string): Observable<Array<Customer>> {
    console.log(environment.backendHost)
    return this.http.get<Array<Customer>>(environment.backendHost + "customers/search?keyword=" + kw);
  }

  public saveCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(environment.backendHost + "customer", customer);
  }
  public deleteCustomer(id: number) {
    return this.http.delete(environment.backendHost + "customer/"+id);
  }
}
