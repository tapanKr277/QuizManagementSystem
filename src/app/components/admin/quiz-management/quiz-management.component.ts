import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AdminService } from '../../../service/admin/admin.service';
import { ModalService } from '../../../service/modal/modal.service';

@Component({
  selector: 'app-quiz-management',
  templateUrl: './quiz-management.component.html',
  styleUrl: './quiz-management.component.css'
})
export class QuizManagementComponent implements OnInit{
  quizzes = [];

  isLoading = false;
  isDeleteModalOpen = false;
  selectedQuiz;
  title = "";
  isQuizModalOpen = false;
  constructor(private adminService: AdminService, private modalService:ModalService) {
    
  }
  ngOnInit(): void {
    this.getAllQuizList();
    this.modalService.deleteModalStatus$.subscribe(status=>{
      this.isDeleteModalOpen = status;
    })

    this.modalService.quizModalStatus$.subscribe(status=>{
      this.isQuizModalOpen = status;
    })
   
  }

  isDelete(event){
    this.isDelete
  }

  openEditQuizModal(quiz){
    this.selectedQuiz = quiz;
    this.modalService.setQuizModalOpen(true);
  }

  deleteQuizRecord(id){ 
    this.title = id;
    this.modalService.setDeleteModalOpen(true);
  }

  onCloseModal(){
    this.modalService.setDeleteModalOpen(false);
    this.modalService.setQuizModalOpen(false);
  }

  getAllQuizList(){
    this.isLoading = true;
    this.adminService.getAllQuizList().subscribe(res=>{
      this.quizzes = res;
      this.isLoading = false;
    }, err=>{
      console.log(err);
      this.isLoading = false;
    })
  }
}
