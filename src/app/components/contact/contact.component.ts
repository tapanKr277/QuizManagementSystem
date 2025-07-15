import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../service/auth/auth.service';
import { SnackbarService } from '../../service/snackbar/snackbar.service';
import { error } from 'console';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.css'
})
export class ContactComponent {
  isLoading: boolean = false;
  contactUsForm: FormGroup;

  constructor(fb: FormBuilder, private authService: AuthService, private snackBar: SnackbarService) {
    this.contactUsForm = fb.group({
      'sender_email':['', [Validators.required, Validators.email]],
      'sender_name':['', Validators.required],
      'message':['', [Validators.required, Validators.minLength(10)]]
    })
  }
  onSubmit(){
    this.isLoading = true;
    const { sender_name, sender_email, message } = this.contactUsForm.value;
    this.authService.contactUs(sender_name, sender_email, message).subscribe(
      (res) => { 
      this.isLoading = false;
      this.contactUsForm.reset();
      this.snackBar.openSnackBar("Thank you for reaching out! Your message has been sent successfully.");
   
    }, error=>{
      this.isLoading = false;
      this.snackBar.openSnackBar(error.error.error);
    })
  }
}
