import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ModalService } from '../../../service/modal/modal.service';

@Component({
  selector: 'app-edit-quiz-form-modal',
  templateUrl: './edit-quiz-form-modal.component.html',
  styleUrl: './edit-quiz-form-modal.component.css'
})
export class EditQuizFormModalComponent implements OnInit{

    isQuizModalOpen: boolean = false;  // Whether the modal is open or not
    @Input() selectedQuiz: any;  // The role object to be edited



    constructor(private modalService: ModalService) {
      
    }
    ngOnInit(): void {
      
      this.modalService.quizModalStatus$.subscribe(status=>{
        this.isQuizModalOpen = status;
      })
    }


    saveChanges() {
     this.modalService.setQuizModalOpen(false);
    }

    close() {
      this.modalService.setQuizModalOpen(false);
    }
}
