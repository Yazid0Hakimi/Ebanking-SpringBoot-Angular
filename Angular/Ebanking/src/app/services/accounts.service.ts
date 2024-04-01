import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {AccountDetails} from "../models/account/account.module";

@Injectable({
  providedIn: 'root'
})
export class AccountsService {
  constructor(private http: HttpClient) {
  }

  public getAccount(accountId: string, page: number , size: number): Observable<AccountDetails> {
    return this.http.get<AccountDetails>(environment.backendHost + "accounts/" + accountId + "/pageOperations?page=" + page + "&size=" + size);
  }
}
