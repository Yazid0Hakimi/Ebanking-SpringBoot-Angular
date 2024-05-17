import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AccountsComponent} from './accounts/accounts.component';
import {NavbarComponent} from './navbar/navbar.component';
import {CustomersComponent} from "./customers/customers.component";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import { NewCostomerComponent } from './new-customer/new-costomer.component';
import { CustomerAccountsComponent } from './customer-accounts/customer-accounts.component';
import { LoginComponent } from './login/login.component';
import { AdminTemplateComponent } from './admin-template/admin-template.component';
import {AuthInterceptor} from "./interceptos/app-http.interceptor";
import {AuthenticationGuard} from "./guards/authentication.guard";
import { EmployeComponent } from './employe/employe.component';
import { ReservationComponent } from './reservation/reservation.component';
import { EquipementComponent } from './equipement/equipement.component';
import { SalleComponent } from './salle/salle.component';
@NgModule({
  declarations: [
    AppComponent,
    CustomersComponent,
    AccountsComponent,
    NavbarComponent,
    NewCostomerComponent,
    CustomerAccountsComponent,
    LoginComponent,
    AdminTemplateComponent,
    EmployeComponent,
    ReservationComponent,
    EquipementComponent,
    SalleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    AuthenticationGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
