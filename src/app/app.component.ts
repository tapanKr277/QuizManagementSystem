import { Component, OnDestroy, OnInit } from '@angular/core';
import { AuthService } from './service/auth/auth.service';
import { Router } from '@angular/router';
import { NavbarService } from './service/navbar/navbar.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  private tokenValidationInterval: any;
  private refreshTokeninterval: any;
  isLoading: boolean = false;  // Add isLoading flag to track the validation process
  isNavbarOpen = true;

  constructor(
    private authService: AuthService, 
    private router: Router, 
    private navbarSerivce: NavbarService
  ) {}

  ngOnInit() {
    if (this.authService.hasToken()) {
      this.startTokenRefresh();
      this.validateToken();
    }
    // Set up an interval to validate the token every 3 minutes
    this.tokenValidationInterval = setInterval(() => {
      this.validateToken();
    }, 170000); // Check token every 2 minutes 50 seconds
    this.navbarSerivce.getNavbarStatus().subscribe(status => {
      this.isNavbarOpen = status;
    });
  }

  ngOnDestroy() {
    // Clear interval when the component is destroyed
    if (this.tokenValidationInterval) {
      clearInterval(this.tokenValidationInterval);
      this.tokenValidationInterval = null;
    }
    if (this.refreshTokeninterval) {
      clearInterval(this.refreshTokeninterval);
      this.refreshTokeninterval = null;
    }
    this.navbarSerivce.setNavbarStatus(true);
    this.authService.setIsAdminLogin(false);
  }

  private validateToken() {
    if (this.authService.hasToken()) {
      
      this.isLoading = true;  // Start loading
      this.authService.validateToken().subscribe(
        (res) => {
          // Token is valid
          const isAdminLogin = res['isAdminLogin'] || false;
          console.log("Token is valid");
          this.authService.isLoggedIn.next(true);
          this.authService.setIsAdminLogin(isAdminLogin);
          this.isLoading = false;  // End loading
        },
        (error) => {
          // Token is invalid or expired
          console.log("Token is invalid or expired");
          this.authService.logout();
          this.isLoading = false;  // End loading
        }
      );
    }
  }

  private startTokenRefresh() {
    if (this.authService.hasToken()) {
      this.refreshTokeninterval = setInterval(() => {
        this.updateRefreshAndAccessToken();
      }, 160000); // Call refresh function every 5 seconds
    }
  }

  private updateRefreshAndAccessToken() {
    if (this.authService.hasToken()) {
      const refreshToken = this.authService.getRefreshToken();
      const email = this.authService.getEmail();

      if (refreshToken && email) {
        console.log('Refreshing token...');
        this.authService.updateRefreshTokenAndAccessToken(refreshToken, email).subscribe(
          (res) => {
            // Update the access and refresh tokens in the storage
            this.authService.setDataAfterLogin(res);
          },
          (error) => {
            console.error('Token refresh failed', error);
            this.logout();
          }
        );
      }
    }
  }

  private logout() {
    this.authService.logout();
    if (this.refreshTokeninterval) {
      clearInterval(this.refreshTokeninterval);
      this.refreshTokeninterval = null;
    }
    if (this.tokenValidationInterval) {
      clearInterval(this.tokenValidationInterval);
      this.tokenValidationInterval = null;
    }
    this.router.navigate(['/login']);
  }
}
