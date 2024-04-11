import {Component} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {catchError, Observable, throwError} from "rxjs";
import {AccountDetails, CustomerAccounts} from "../models/account/account.module";
import {CustomerService} from "../services/customer.service";

@Component({
  selector: 'app-customer-accounts',
  templateUrl: './customer-accounts.component.html',
  styleUrl: './customer-accounts.component.css'
})
export class CustomerAccountsComponent {
  customerId!: number;
  customerAccounts$!: Observable<Array<CustomerAccounts>>;
  errorMessage!: string;
  constructor(private route: ActivatedRoute, private customerService: CustomerService, private router : Router) {
  }

  ngOnInit(): void {
    this.customerId = this.route.snapshot.params['id'];
    this.customerAccounts$ = this.customerService.getCustomerAccounts(this.customerId).pipe(catchError(err => {
      this.errorMessage = err.message;
      return throwError(err);
    }));
  }

  handleCustomerAccountOperations(id: string) {
    this.router.navigateByUrl('/accounts/' + id);
  }
}
