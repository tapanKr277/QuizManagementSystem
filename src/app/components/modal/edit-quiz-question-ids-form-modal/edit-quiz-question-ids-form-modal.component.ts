import { Component } from '@angular/core';
import { Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-edit-quiz-question-ids-form-modal',
  templateUrl: './edit-quiz-question-ids-form-modal.component.html',
  styleUrl: './edit-quiz-question-ids-form-modal.component.css'
})
export class EditQuizQuestionIdsFormModalComponent {
  @Input() isModalOpen: boolean = false; // Whether the modal is open or not
  @Input() selectedQuizQuestion: any;
  @Output() closeModal: EventEmitter<boolean> = new EventEmitter(); // Close modal event



  // To save the changes made in the form
  saveChanges() {
    console.log('Quiz-Question relationship updated:', this.selectedQuizQuestion);
    this.closeModal.emit();  // Emit event to close the modal after saving changes
  }

  // Close the modal
  close() {
    this.closeModal.emit(); // Emit event to close the modal
  }
}
