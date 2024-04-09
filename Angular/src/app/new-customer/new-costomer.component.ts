import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CustomerService} from "../services/customer.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-costomer',
  templateUrl: './new-costomer.component.html',
  styleUrl: './new-costomer.component.css'
})
export class NewCostomerComponent {
  errorMessage!: string;
  newCustomersformGroup!: FormGroup

  constructor(private customerService: CustomerService, private fb: FormBuilder ,private router: Router) {
  }

  ngOnInit(): void {
    this.newCustomersformGroup = this.fb.group({
      name: this.fb.control("",[Validators.required, Validators.minLength(4)]),
      email: this.fb.control("", [Validators.email])
    })
  }

  handleSaveCustomers() {
    let customer = this.newCustomersformGroup.value;
    this.customerService.saveCustomer(customer).subscribe({
      next: data => {
        alert("Customer added successfully");
        this.newCustomersformGroup.reset()
        this.router.navigateByUrl("/customers")
      },
      error: err => {
        console.log('error')

      }
    })
  }
}


