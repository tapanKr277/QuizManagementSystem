import { inject, Injectable } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { AuthService } from '../service/auth/auth.service';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';

export const authGuard: CanActivateFn = (route, state) => {

  const authService = inject(AuthService);
  const router = inject(Router);

  // Check if the user is logged in
  return authService.isLoggedIn.pipe(
    map(isLoggedIn => {
      if (isLoggedIn) {
        return true;  // Allow access
      } else {
        router.navigate(['/login']);  // Redirect to login if not logged in
        return false;  // Block access
      }
    })
  );
};
