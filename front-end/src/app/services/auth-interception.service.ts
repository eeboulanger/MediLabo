import {inject} from "@angular/core";
import {HttpHandlerFn, HttpRequest} from "@angular/common/http";
import {AuthService} from "../security/auth.service";
import {Router} from "@angular/router";
import {throwError} from "rxjs";

/**
 * Adds authorization token as header to all http requests
 */
export function AuthInterceptionService(req: HttpRequest<unknown>, next: HttpHandlerFn) {
  // Get Auth token from AuthService
  const authToken = inject(AuthService).getAuthToken();

  // If not authenticated, redirect to login
  if (!authToken) {
    console.log("No authentication token provided");
    return throwError(() => new Error('User is not authenticated'));
  }

  // Clone the request to add the authentication header.
  const cloned = req.clone({
    headers: req.headers.set('Authorization', `Basic ` + authToken)
  });
  return next(cloned);
}
