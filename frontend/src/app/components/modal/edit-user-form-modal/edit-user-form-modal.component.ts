import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ModalService } from '../../../service/modal/modal.service';
import { UserService } from '../../../service/user/user.service';

@Component({
  selector: 'app-edit-user-form-modal',
  templateUrl: './edit-user-form-modal.component.html',
  styleUrls: ['./edit-user-form-modal.component.css']
})
export class EditUserFormModalComponent implements OnInit {

  @Output() userUpdated = new EventEmitter<boolean>(false);
  isUserModalOpen = false;
  @Input() selectedUser: any = {}; 
  isLoading = false;

  userForm: FormGroup; 

  constructor(private fb: FormBuilder,
    private modalService: ModalService,
    private userService: UserService) { }

  ngOnInit(): void {
    this.modalService.userModalStatus$.subscribe(status => {
      this.isUserModalOpen = status;
    })

    // Initialize the form
    this.userForm = this.fb.group({
      userId: [{ value: '' }],  // Read-only field
      username: [{ value: '' }, [Validators.required, Validators.minLength(3)]],
      firstName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30)]],
      lastName: ['', [Validators.required, Validators.maxLength(30)]],
      // password: ['', [Validators.required, Validators.minLength(6)]],
      phoneNumber: ['', [Validators.required, Validators.pattern(/^[0-9]{10}$/)]],  // Phone number validation
      isActive: [{ value: false }],
      isVerified: [{ value: false }],
      isOtpVerified: [{ value: false }],
      otpLastUpdate: [{ value: null }],
      email: ['', [Validators.required, Validators.email]],
      createdAt: [{ value: null, disabled: true }],
      lastUpdate: [{ value: null, disabled: true }]
    });

    // If selectedUser exists (i.e., we are editing), populate the form with its data
    if (this.selectedUser && Object.keys(this.selectedUser).length > 0) {
      this.populateForm(this.selectedUser);
    }
  }

  // Method to populate the form with the selectedUser's values
  private populateForm(user: any): void {
    // Ensure userId is included in the form data
    this.userForm.patchValue({
      userId: user.userId,  // Manually patch if needed
      username: user.username,
      firstName: user.firstName,
      lastName: user.lastName,
      phoneNumber: user.phoneNumber,
      email: user.email,
      isActive: user.isActive,
      isVerified: user.isVerified,
      isOtpVerified: user.isOtpVerified,
      otpLastUpdate: user.otpLastUpdate,
      createdAt: user.createdAt,
      lastUpdate: user.lastUpdate
    });
  }


  // Close the modal
  close() {
    this.modalService.setUserModalOpen(false);

  }

  // Save changes (either create or update)
  saveChanges() {
    if (this.userForm.valid) {
      const formData = this.userForm.value;
      this.isLoading = true;
      this.userService.updateUserDataAdmin(formData).subscribe(res => {
        this.populateForm(res);
        this.isLoading = false;
        this.userUpdated.emit(true);
      }, error => {
        this.isLoading = false;
        console.log(error);
      })
    }
  }

  // Method to open modal for editing an existing user
  openEditModal(user: any): void {
    this.selectedUser = { ...user };
    this.populateForm(user); // Populate form with selected user values
  }

}
