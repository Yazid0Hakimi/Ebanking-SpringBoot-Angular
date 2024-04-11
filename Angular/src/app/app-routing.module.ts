import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CustomersComponent} from "./customers/customers.component";
import {AccountsComponent} from "./accounts/accounts.component";
import {NewCostomerComponent} from "./new-customer/new-costomer.component";
import {CustomerAccountsComponent} from "./customer-accounts/customer-accounts.component";

const routes: Routes = [
  {path: "customers", component: CustomersComponent},
  {path: "accounts", component: AccountsComponent},
  {path: "accounts/:id", component: AccountsComponent},
  {path: "new-custumer", component: NewCostomerComponent},
  {path: "customer-accounts/:id", component: CustomerAccountsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
