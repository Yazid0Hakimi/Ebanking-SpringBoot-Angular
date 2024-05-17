import {Component} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {group} from "@angular/animations";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginform!: FormGroup

  constructor(private fb: FormBuilder, private authService: AuthService,private router: Router) {
  }


  ngOnInit(): void {
    this.loginform = this.fb.group({
      username: this.fb.control(''),
      password: this.fb.control('')
    });
  }

  handleLogin() {
    let username = this.loginform.value.username;
    let password = this.loginform.value.password;
    this.authService.login(username, password)
      .subscribe({
        next: data => {
          this.authService.loadProfile(data)
          console.log(data)
          this.router.navigateByUrl("/employe")
        },
        error: err => {
          console.log(err);
        }
      });
  }
}

