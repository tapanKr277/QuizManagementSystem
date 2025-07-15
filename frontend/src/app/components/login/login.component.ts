import { Component, OnInit } from '@angular/core';
import { error } from 'console';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth/auth.service';
import { SnackbarService } from '../../service/snackbar/snackbar.service';
import { OauthService } from '../../service/oauth/oauth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{


  loginForm: FormGroup;
  isLoading: boolean = false;
  isPasswordVisible: boolean = false;

  constructor(private fb: FormBuilder,
            private authService: AuthService, 
            private router: Router,
            private oAuthService: OauthService,
            private snackBar: SnackbarService) {

  }


 ngOnInit(): void {
  this.loginForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],  // Email field with required and email validation
    password: ['', [Validators.required, Validators.minLength(4)]]  // Password field with required and min length validation
  });
    if (this.authService.hasToken()) {
      this.isLoading = true;
      this.authService.validateToken().subscribe(
        (res) => {
          this.authService.isLoggedIn.next(true);
          this.isLoading = false;
          this.handleLoginSuccess();
        },
        (error) => {
          this.authService.logout();
          this.isLoading = false;
        }
      );
    }
  }


  togglePasswordVisibility(){
    this.isPasswordVisible = !this.isPasswordVisible;

  }

  onLogin() {
    this.isLoading = true;

    // Disable form to prevent multiple submissions
    const formData = this.loginForm.value;
    this.authService.login(formData.email, formData.password).subscribe(
      (res) => {
        this.authService.setDataAfterLogin(res);
        this.handleLoginSuccess();
      },
      (error) => {
        this.handleLoginError(error);
      }
    );
  }

    onGoogleLogin(): void {
    this.oAuthService.signInWithGoogle();
  }

  onGithubLogin(): void {
    this.oAuthService.signInWithGithub();
  }


  private handleLoginError(error: any) {
    console.log(error);
    this.isLoading = false;

    this.snackBar.openSnackBar(error.error.error);
    this.authService.logout();  // Logout if there's an error
  }

  private handleLoginSuccess() {
    this.isLoading = false;
    this.router.navigate(['/home']);  // Navigate to home page after successful login
    this.snackBar.openSnackBar("Login Successful");
  }

  // You may want to call logout from a single place to handle session clearing.
  private logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
