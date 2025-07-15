import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PasswordService } from '../../service/password/password.service';
import { error } from 'console';
import { Router } from '@angular/router';
import { SnackbarService } from '../../service/snackbar/snackbar.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.css'
})
export class ForgotPasswordComponent implements OnInit{
  
  forgotPasswordForm: FormGroup;
  isLoading: boolean = false;

  constructor(private fb: FormBuilder, private passwordService: PasswordService,
    private router: Router,
    private snackBar: SnackbarService
  ) {
    
  }
  ngOnInit(): void {
    this.forgotPasswordForm = this.fb.group({
      "email": ['', [Validators.required, Validators.email, Validators.minLength(10), Validators.maxLength(30)]]
    })
  }

  onSubmit(){
    this.isLoading = true;
    this.passwordService.forgotPassword(this.forgotPasswordForm.value.email).subscribe((res)=>{
      this.router.navigate(['/verify-otp', this.forgotPasswordForm.value.email]);
      this.snackBar.openSnackBar("OTP has been sent to your registered email.");
      this.isLoading = false;
    }, (error)=>{
      this.isLoading = false;
      this.snackBar.openSnackBar(error.error.error);
      
    })
  }
  
}
