import { Component, ViewChild, ElementRef, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PasswordService } from '../../service/password/password.service';
import { error } from 'console';
import { SnackbarService } from '../../service/snackbar/snackbar.service';

@Component({
  selector: 'app-verify-otp',
  templateUrl: './verify-otp.component.html',
  styleUrls: ['./verify-otp.component.css']
})
export class VerifyOtpComponent implements OnInit {

  isLoading: boolean = false;
  otp1: string = '';
  otp2: string = '';
  otp3: string = '';
  otp4: string = '';

  isResend: boolean = false;
  countdown: number = 60;

  @ViewChild('otp1Ref') otp1Ref!: ElementRef;
  @ViewChild('otp2Ref') otp2Ref!: ElementRef;
  @ViewChild('otp3Ref') otp3Ref!: ElementRef;
  @ViewChild('otp4Ref') otp4Ref!: ElementRef;


  email: string = null;

  ngOnInit() {
    this.startCountdown();
    this.route.paramMap.subscribe(param=>{
      this.email = param.get('email');
    })
  }

  constructor(private router: Router, private route: ActivatedRoute, private passwordSerivce: PasswordService, private snackBar: SnackbarService) {
    
  }

  startCountdown() {
    const interval = setInterval(() => {
      this.countdown--;
      if (this.countdown === 0) {
        clearInterval(interval);
        this.isResend = true;  
      }
    }, 1000); 
  }

  moveFocus(event: any, nextInputId: string) {
    if (event.target.value.length === 1) {
      const nextInput = document.getElementById(nextInputId) as HTMLInputElement;
      if (nextInput) {
        nextInput.focus();
      }
    }
  }

  verifyOtp() {
    this.isLoading = true;
    const otp = this.otp1+this.otp2+this.otp3+this.otp4;
    this.passwordSerivce.verifyOtp(this.email, otp).subscribe((res)=>{
      this.isLoading = false;
      this.router.navigate(['/create-password', this.email]);
      this.snackBar.openSnackBar("OTP has been verified successfully.")
    }, (error)=>{
      this.isLoading = false;
      this.snackBar.openSnackBar(error.error.error);
    })
  }

  resendOtp() {
    this.isLoading = true;
    this.passwordSerivce.reSendOtp(this.email).subscribe((res)=>{
      this.isLoading = false;
      this.snackBar.openSnackBar("A new OTP has been sent to your email.")
      this.countdown = 60;  
      this.isResend = false; 
      this.startCountdown(); 
    }, (error)=>{
      this.isLoading = false;
      this.countdown = 60;  
      this.isResend = false; 
      this.startCountdown(); 
      this.snackBar.openSnackBar(error.error.error);
    })
    
  }
}
