import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, RequiredValidator, Validators } from '@angular/forms';
import { UserService } from '../../service/user/user.service';
import { AuthService } from '../../service/auth/auth.service';
import { SnackbarService } from '../../service/snackbar/snackbar.service';
import { error } from 'console';
import { Router } from '@angular/router';
import { OauthService } from '../../service/oauth/oauth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']  
})
export class RegisterComponent implements OnInit{

  num = 1; 
  currentStep: number = 1;
  isLoading: boolean = false;
  registrationForm: FormGroup;

  constructor(private oAuthService: OauthService, private snackBar: SnackbarService, private fb: FormBuilder, private userService: UserService, private authService: AuthService, private router: Router) {
    this.registrationForm = fb.group({
      "first_name": ["", Validators.required],
      "last_name" : ["", Validators.required],
      "phone_number": ["", Validators.required],
      "username": ["", Validators.required],
      "email": ["",[Validators.required, Validators.email]],
      "password": [""],
      "confirm_password": [""],
    })
  }
  ngOnInit(): void {
    this.authService.logout();
  }



  nextStep() {
    if (this.currentStep < 2) {
      this.currentStep++;
      this.num++;
    }
  }

  previousStep() {
    if (this.currentStep > 1) {
      this.currentStep--;
      this.num--;
    }
  }

  async onSubmit() {
    this.isLoading = true;
    const userData = {
      username: this.registrationForm.value.username,
      password: this.registrationForm.value.password,
      phoneNumber: this.registrationForm.value.phone_number,
      email: this.registrationForm.value.email,
      studentDto: {
        firstName: this.registrationForm.value.first_name,
        lastName: this.registrationForm.value.last_name,
      }
    };

    this.userService.createUser(userData).subscribe(res=>{
      this.isLoading = false;
      this.router.navigate(['/login']);
      this.snackBar.openSnackBar(res?.success);
      
    }, error =>{
      this.isLoading = false;
      this.snackBar.openSnackBar(error.error.error);
    })
  
  }

  onGoogleLogin(): void {
    this.oAuthService.signInWithGoogle();
  }

  onGithubLogin(): void {
    this.oAuthService.signInWithGithub();
  }

}
