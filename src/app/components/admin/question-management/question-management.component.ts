import { Component, Input, OnInit } from '@angular/core';
import { AdminService } from '../../../service/admin/admin.service';
import { error } from 'console';
import { ModalService } from '../../../service/modal/modal.service';

@Component({
  selector: 'app-question-management',
  templateUrl: './question-management.component.html',
  styleUrl: './question-management.component.css'
})
export class QuestionManagementComponent implements OnInit{


  questions  = [];
  isLoading = false;
  isDeleteModalOpen = false;
  selectedQuestion;
  isQuestionModalOpen = false;
  title ;

  openEditQuestionModal(question){
    this.selectedQuestion = question;
    this.modalService.setQuestionModalOpen(true);
  }

  deleteQuestionRecord(id){
    this.title = id;
    this.modalService.setDeleteModalOpen(true);
  }

  onCloseModal(event){
    this.modalService.setDeleteModalOpen(false);
    this.modalService.setQuestionModalOpen(false);
  }

  constructor(private adminService: AdminService, private modalService: ModalService) {
    
  }
  ngOnInit(): void {
    this.getAllQuestionList();
    this.modalService.deleteModalStatus$.subscribe(status=>{
      this.isDeleteModalOpen = status;
    })
    this.modalService.questionModalStatus$.subscribe(status=>{
      this.isQuestionModalOpen = status;
    })
  }

  getAllQuestionList(){
    this.isLoading = true;
    this.adminService.getAllQuestionList().subscribe(res=>{
      this.questions = res;
      this.isLoading = false;
    }, err =>{
      console.log(err);
      this.isLoading = false;
    })
  }
}
