import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AccountsComponent} from './accounts/accounts.component';
import {NavbarComponent} from './navbar/navbar.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import { LoginComponent } from './login/login.component';
import {AuthInterceptor} from "./interceptos/app-http.interceptor";
import {AuthenticationGuard} from "./guards/authentication.guard";
import { EmployeComponent } from './employe/employe.component';
import { SalleComponent } from './salle/salle.component';
import { ReservationComponent } from './reservation/reservation.component';
import { EquipementComponent } from './equipement/equipement.component';
@NgModule({
  declarations: [
    AppComponent,
    AccountsComponent,
    NavbarComponent,
    LoginComponent,
    EmployeComponent,
    SalleComponent,
    ReservationComponent,
    EquipementComponent,
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
