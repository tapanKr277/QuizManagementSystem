import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { AuthService } from '../auth/auth.service';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptor implements HttpInterceptor {

  constructor(
    private authService: AuthService, 
    private router: Router
  ) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const token = localStorage.getItem('token');

    // If we have a token, clone the request and add the Authorization header
    if (token) {
      const clonedRequest = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });

      return next.handle(clonedRequest).pipe(
        catchError(error => {
          // Handle unauthorized error due to blacklisting or expiration
          if (error.status === 401) {
            // If the user is unauthorized (e.g., token blacklisted or expired), log them out
            this.authService.logout();
            this.router.navigate(['/login']);
          }
          throw error; // Rethrow error for further handling in the app if necessary
        })
      );
    }

    // If no token, simply pass the request through
    return next.handle(req);
  }
}
