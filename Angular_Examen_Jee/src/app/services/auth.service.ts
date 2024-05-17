import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {jwtDecode} from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isAuthenticated: boolean = false
  roles: any;
  username: any;
  accessToken!: any;

  constructor(private http: HttpClient) {
  }

  public login(username: string, password: string) {
    let options = new HttpHeaders().set("content-type", "application/x-www-form-urlencoded");
    let params = new HttpParams()
      .set('username', username).set('password', password);

    return this.http.post(
      'http://localhost:8082/auth/login',
      params, {headers: options});
  }

  loadProfile(req: any) {
    this.isAuthenticated = true;

    this.accessToken = req['access-token'];
    let data: any = jwtDecode(this.accessToken);
    this.roles = data.scope;
    this.username = data.sub;
  }

  logout() {
    this.isAuthenticated = false;
    this.roles = undefined;
    this.accessToken = undefined;
    this.username = undefined;
  }
}
