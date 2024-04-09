import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AccountsComponent} from './accounts/accounts.component';
import {NavbarComponent} from './navbar/navbar.component';
import {CustomersComponent} from "./customers/customers.component";
import {HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import { NewCostomerComponent } from './new-customer/new-costomer.component';
import { StandtestComponent } from './standtest/standtest.component';
@NgModule({
  declarations: [
    AppComponent,
    CustomersComponent,
    AccountsComponent,
    NavbarComponent,
    NewCostomerComponent,
    StandtestComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
