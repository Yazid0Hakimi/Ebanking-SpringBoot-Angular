import {Component, OnInit, signal} from '@angular/core';
import {CustomerService} from "../services/customer.service";
import {catchError, map, Observable, throwError} from "rxjs";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Customer} from "../models/customer/customer.module";
import {Router} from "@angular/router";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css'] // Fix the property name here
})
export class CustomersComponent implements OnInit {
  custumers$!: Observable<Customer[]>;
  errorMessage!: string;
  searchformGroup: FormGroup | undefined

  constructor(private customerService: CustomerService, private fb: FormBuilder, private router: Router) {
  }

  ngOnInit(): void {
    this.searchformGroup = this.fb.group({
      keyword: this.fb.control("")
    })
    this.custumers$ = this.customerService.getCustomers().pipe(catchError(err => {
      this.errorMessage = err.message;
      return throwError(err);
    }));
  }

  handleSearchCustomers() {
    let kw = this.searchformGroup?.value;
    this.custumers$ = this.customerService.getSearchCustomers(kw.keyword).pipe(
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(err);
      }));
  }

  handleDeleteCustomer(c: Customer) {
    this.customerService.deleteCustomer(c.id).subscribe(
      () => {
        this.custumers$ = this.custumers$.pipe(
          map((data: any) => {
            let index = data.indexOf(c);
            data.slice(index, 1);
            return data;
          })
        );
      },
      (error) => {
        alert("Error in deleting Customer ");
      }
    );
  }
  handleAccountOperations(c: Customer) {
    this.router.navigateByUrl('customer-accounts/' + c.id)
  }
}
