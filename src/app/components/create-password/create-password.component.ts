import { Component, OnInit } from '@angular/core';
import { PasswordService } from '../../service/password/password.service';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SnackbarService } from '../../service/snackbar/snackbar.service';

@Component({
  selector: 'app-create-password',
  templateUrl: './create-password.component.html',
  styleUrl: './create-password.component.css'
})
export class CreatePasswordComponent implements OnInit {

  isPasswordVisible = false;
  changePasswordForm: FormGroup;
  email: string = null;
  isLoading: boolean = false;
  constructor(private fb: FormBuilder, private passwordService: PasswordService,
     private router: Router,
    private route: ActivatedRoute,
    private snackBar: SnackbarService) {
    
  }
  ngOnInit(): void {
    this.route.paramMap.subscribe(param=>{
      this.email = param.get('email');
    });

    this.changePasswordForm = this.fb.group({
      "password": ['', [Validators.required, Validators.minLength(8)]],
      "confirm_password": ['', [Validators.required, Validators.minLength(8)]],
    })
  }

  onSubmit(){
    this.isLoading = true;
    const formData = this.changePasswordForm.value;
    this.passwordService.changePassword(this.email, formData.password, formData.confirm_password).subscribe((res)=>{
      this.isLoading = false;
      this.snackBar.openSnackBar("Password Changed Successfully");
      this.router.navigate(['/login']);
    },(error)=>{
      this.isLoading = false;
      this.snackBar.openSnackBar(error.error.error);
    })
  }


  togglePasswordVisibility(){
    this.isPasswordVisible = !this.isPasswordVisible;
  }

} 
