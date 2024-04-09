import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AccountsService} from "../services/accounts.service";
import {catchError, Observable, throwError} from "rxjs";
import {AccountDetails} from "../models/account/account.module";

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
  accountFormGroup!: FormGroup;
  account$!: Observable<AccountDetails>;
  errorMessage!: string;
  currentPage: number = 0;
  pageSize: number = 5;
  operationFormGroup!: FormGroup; // Ensure proper initialization

  constructor(private fb: FormBuilder, private accountService: AccountsService) {
  }

  ngOnInit(): void {
    this.accountFormGroup = this.fb.group({
      accountId: [''] // Initialize with default value
    });

    this.operationFormGroup = this.fb.group({
      operationType: [''],
      amount: [0],
      description: [''],
      accountDestination: ['']
    });

  }

  handleSearchAccount(): void {
    const accountId = this.accountFormGroup.value;
    this.account$ = this.accountService.getAccount(accountId.accountId, this.currentPage, this.pageSize)
      .pipe(
        catchError(err => {
          this.errorMessage = err.message;
          return throwError(err);
        })
      );
  }

  gotoPage(page: number): void {
    this.currentPage = page;
    this.handleSearchAccount();
  }

  handleAccountOperation(): void {
    let accountId: string = this.accountFormGroup.value.accountId;
    let operationType: string = this.operationFormGroup.value.operationType;
    let amount: number = this.operationFormGroup.value.amount;
    let description: string = this.operationFormGroup.value.description;
    let accountDestination: string = this.operationFormGroup.value.accountDestination;

    if (operationType == 'DEBIT') {
      this.accountService.debit(accountId, amount, description).subscribe({
        next: (data) => {
          alert("Debit operation successful");
        },
        error: (error) => {
          this.errorMessage = error.message;
          console.error('There was an error!', error);
        }
      })
    } else if (operationType == 'CREDIT') {
      this.accountService.credit(accountId, amount, description)
    } else {
      this.accountService.transfer(accountId, accountDestination, amount, description)
    }
  }
}
