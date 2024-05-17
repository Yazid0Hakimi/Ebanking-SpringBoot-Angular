import {CanActivateFn, Router} from '@angular/router';
import {Injectable} from '@angular/core';
import {AuthService} from "../services/auth.service";

@Injectable()
export class AuthenticationGuard {

  constructor(private authService: AuthService, private router: Router) {
  }

  canActivate: CanActivateFn =
    (route, state) => {
      // Check if the user is authenticated using AuthService
      if (this.authService.isAuthenticated == true) {
        return true; // Allow navigation
      } else {
        this.router.navigateByUrl("/login");
        return false; // Prevent navigation
      }
    };
}
