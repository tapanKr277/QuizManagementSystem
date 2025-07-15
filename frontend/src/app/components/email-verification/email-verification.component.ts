import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../service/auth/auth.service';
import { SnackbarService } from '../../service/snackbar/snackbar.service';

@Component({
  selector: 'app-email-verification',
  templateUrl: './email-verification.component.html',
  styleUrl: './email-verification.component.css'
})
export class EmailVerificationComponent implements OnInit {


  token: string = null;
  email: string = null;
  isLoading : boolean = false;
  interval: any = null;

  constructor(private activatedRoute: ActivatedRoute, 
      private router: Router,
      private authService: AuthService,
      private snackBar: SnackbarService) {
    
  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.token = params['token']; 
      this.email = params['email']; 
    });
    if(this.token!=null && this.email!=null){
      this.verifyEmail(this.email, this.token);
    }
    else{
      this.router.navigate(["/page-not-found"]);
    }
  }

  verifyEmail(email, token){
    this.isLoading = true;
    this.authService.verifyToken(email, token).subscribe(res=>{
      this.isLoading = false;
      this.snackBar.openSnackBar("Email verified successfully");
      this.startTimer();
    }, err =>{
      this.isLoading = false;
      this.router.navigate(['/page-not-found']);
      this.snackBar.openSnackBar(err.error.error);
    })
  }


  startTimer() {
    this.interval = setTimeout(() => {
      this.navigate();
    }, 3000); 
  }

  navigate(){
    this.router.navigate(['/login']);
  }

}
