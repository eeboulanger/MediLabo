import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root',
})

/**
 * Responsible for storing and setting the authorization token of users
 */
export class AuthService {
  apiUrl = "http://localhost:8080/patients";
  authenticated = false;

  constructor(private http: HttpClient, private router: Router) {
  }

  getAuthToken() {
    return localStorage.getItem("id_token");
  }

  isAuthenticated(): boolean {
    return this.authenticated;
  }

  logout() {
    localStorage.removeItem("id_token");
    this.authenticated = false;
    this.router.navigateByUrl("/");
  }

  /** Sends request with credentials and if response is ok, sets authenticated to true and stores the credentials in the token
   * @param credentials are the username och password
   */
  authenticate(credentials: { username: any; password: any; }): Observable<any> {
    localStorage.setItem('id_token', btoa(credentials.username + ':' + credentials.password));

    return this.http.get(this.apiUrl, {observe: 'response'}).pipe(
      map((response) => {
        this.authenticated = response.status === 200;
      }),
      catchError(() => {
        return throwError(() => new Error('Login failed, please try again'));
      })
    );
  }
}
