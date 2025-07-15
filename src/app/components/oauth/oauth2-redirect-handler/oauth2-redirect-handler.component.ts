import { Component } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { AuthService } from '../../../service/auth/auth.service';
import { SnackbarService } from '../../../service/snackbar/snackbar.service';

@Component({
  selector: 'app-oauth2-redirect-handler',
  templateUrl: './oauth2-redirect-handler.component.html',
  styleUrl: './oauth2-redirect-handler.component.css'
})
export class Oauth2RedirectHandlerComponent {

  private token: string | null = null;
  private refreshToken: string | null = null;
  private userId: string | null = null;
  private username: string | null = null;
  private email: string | null = null;

  constructor(private activatedRoute: ActivatedRoute,
    private authService: AuthService,
    private snackBar: SnackbarService,
    private router: Router) {

  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe((params: Params) => {
      this.token = params['token'];
      this.refreshToken = params['refreshToken'];
      this.userId = params['userId'];
      this.username = params['username'];
      this.email = params['email'];
    });
    const data = {
      'accessToken': this.token,
      'refreshToken': this.refreshToken,
      'userId': this.userId,
      'username': this.username,
      'email': this.email
    }
    try {
      this.authService.setDataAfterLogin(data);
      this.handleLoginSuccess();
    }
    catch (error) {
      this.handleLoginError(error);
      console.error("Error setting data after login", error);
    }
  }
  private handleLoginError(error: any) {
    console.log(error);
    this.snackBar.openSnackBar(error.error.error);
    this.authService.logout();  // Logout if there's an error
  }

  private handleLoginSuccess() {
    this.router.navigate(['/home']);  // Navigate to home page after successful login
    this.snackBar.openSnackBar("Login Successful");
  }
}
