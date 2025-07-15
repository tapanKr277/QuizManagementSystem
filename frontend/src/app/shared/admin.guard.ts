import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { AuthService } from '../service/auth/auth.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

export const adminGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  // Use the injected services to determine if the user is an admin
  return authService.isAdminLogin.pipe(
    map(isAdminLoggedin => {
      if (isAdminLoggedin) {
        return true;
      } else {
        authService.logout();  // Optional: Log out if not an admin
        router.navigate(['/login']);  // Redirect to login page
        return false;
      }
    })
  );
};
