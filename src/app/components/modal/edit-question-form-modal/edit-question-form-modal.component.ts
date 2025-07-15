import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ModalService } from '../../../service/modal/modal.service';

@Component({
  selector: 'app-edit-question-form-modal',
  templateUrl: './edit-question-form-modal.component.html',
  styleUrl: './edit-question-form-modal.component.css'
})
export class EditQuestionFormModalComponent implements OnInit{

  isQuestionModalOpen: boolean = false;  // Whether the modal is open or not
  @Input() selectedQuestion: any;  // The question object to be edited
  
  constructor(private modalService: ModalService) {
    
  }
  ngOnInit(): void {
    this.modalService.questionModalStatus$.subscribe(status=>{
      this.isQuestionModalOpen = status;
    })
  }

  saveChanges() {
    this.modalService.setQuestionModalOpen(false);
  }

  close() {
  this.modalService.setQuestionModalOpen(false);
  }
}