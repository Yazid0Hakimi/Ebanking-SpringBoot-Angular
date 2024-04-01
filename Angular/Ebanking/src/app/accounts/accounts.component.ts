import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AccountsService} from "../services/accounts.service";
import {catchError, Observable, throwError} from "rxjs";
import {Customer} from "../models/customer/customer.module";
import {AccountDetails} from "../models/account/account.module";

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.css'
})
export class AccountsComponent implements OnInit {
  accountFormGroup!: FormGroup;
  account$!: Observable<AccountDetails>;
  errorMessage!: string;
  currentPage: number = 0;
  pageSize: number = 5;

  constructor(private fb: FormBuilder, private accountService: AccountsService) {
  }

  ngOnInit(): void {
    this.accountFormGroup = this.fb.group({
      accountId: this.fb.control('')
    });
  }

  public handleSearchAccount(): void {
    let accountId = this.accountFormGroup?.value;
    this.account$ = this.accountService.getAccount(accountId.accountId, this.currentPage, this.pageSize).pipe(catchError(err => {
      this.errorMessage = err.message;
      return throwError(err);
    }));
  }

  public gotoPage(page: number) {
    this.currentPage = page;
    this.handleSearchAccount();
  }
}
