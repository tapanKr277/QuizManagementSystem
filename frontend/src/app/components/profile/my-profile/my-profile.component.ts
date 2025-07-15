import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../service/user/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SnackbarService } from '../../../service/snackbar/snackbar.service';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {
  private userId;
  private user;
  userForm: FormGroup;
  passwordForm: FormGroup;
  isLoading = false;

  constructor(private userService: UserService, private fb: FormBuilder, private snackBar: SnackbarService) {}

  ngOnInit() {
    this.userId = localStorage.getItem("userId");
    if (this.userId != null) {
      this.getUserById(this.userId);
    }

    this.userForm = this.fb.group({
      username: [ {value: '',disabled: true }],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', Validators.required],
    });

    this.passwordForm = this.fb.group({
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
    });
  }

  getUserById(userId: string) {
    this.isLoading = true;
    this.userService.getUserById(userId).subscribe(
      res => {
        this.user = res;
        this.populateForm(this.user);
        this.isLoading = false;
      },
      error => {
        this.isLoading = false;
        console.log(error);
      }
    );
  }

  populateForm(user: any) {
    this.userForm.patchValue({
      username: user.username,
      firstName: user.firstName,
      lastName: user.lastName,
      phoneNumber: user.phoneNumber,
      email: user.email,
    });
  }

  changePassword() {
    // Logic to change password can go here
  }

  saveChanges() {
    if (this.userForm.valid) {
      this.isLoading = true;
      const formData = this.userForm.value;
      this.userService.updateUserData(formData, this.userId).subscribe(res=>{
        this.isLoading = false;
        this.user = res;
        this.populateForm(this.user);
        this.snackBar.openSnackBar("Profile updated successfully");
      })
    } else {
      this.isLoading = false;
      console.log('Form is invalid');
      this.snackBar.openSnackBar("Something went wrong");
    }
  }
}
