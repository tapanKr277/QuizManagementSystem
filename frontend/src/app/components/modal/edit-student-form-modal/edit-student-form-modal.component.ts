import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-edit-student-form-modal',
  templateUrl: './edit-student-form-modal.component.html',
  styleUrl: './edit-student-form-modal.component.css'
})
export class EditStudentFormModalComponent {
  
  selectedStudent: any = {
    firstName: '',
    lastName: '',
    numberOfQuizAttempted: 0,
    averageScore: 0,
    quizIds: [],
    createdAt: new Date(),
    lastUpdate: new Date()
  };

  @Input() isModalOpen: boolean = false;  // Whether the modal is open or not
  @Input() selectedRole: any;  // The role object to be edited
  @Output() closeModal: EventEmitter<boolean> = new EventEmitter();  // Close modal event

  saveChanges() {
    // Logic to save the role (e.g., call API service to update role in the backend)
    console.log('Role updated:', this.selectedRole);
    this.closeModal.emit();  // Emit event to close the modal
  }

  close() {
    this.closeModal.emit();  // Emit event to close the modal
  }
} 
