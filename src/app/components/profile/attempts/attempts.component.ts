import { Component } from '@angular/core';
import { StudentService } from '../../../service/student/student.service';
import { SnackbarService } from '../../../service/snackbar/snackbar.service';
import { Router } from '@angular/router';
import { SidebarService } from '../../../service/sidebar/sidebar.service';
import { QuizService } from '../../../service/quiz/quiz.service';

@Component({
  selector: 'app-attempts',
  templateUrl: './attempts.component.html',
  styleUrl: './attempts.component.css'
})
export class AttemptsComponent {
  isSidenavOpen: boolean = false;
    userId: string = null;
    isLoading : boolean = false;
    quizAttemptList = [];
  
    constructor(private quizService: QuizService,
       private snackBar: SnackbarService, 
       private router: Router,
      private sidebarService: SidebarService) {
      
    }
  
    ngOnDestroy(): void {
      this.sidebarService.setSidebarStatus(false);
    }
  
    ngOnInit(): void {
      if(localStorage.getItem("userId")){
        this.userId = localStorage.getItem('userId');
      }
      if(this.userId){
        this.getStudentQuizAttemptList(this.userId);
      }
  
      this.sidebarService.getSidebarStatus().subscribe(status=>{
        this.isSidenavOpen = status;
      })
      
    }
  
    getStudentQuizAttemptList(userId){
      this.isLoading = true;
      this.quizService.getAllAttemptedQuizsByUser(userId).subscribe((res)=>{
        this.quizAttemptList = res;
        this.isLoading = false;
      }, (error)=>{
        console.log(error);
        this.isLoading = false;
      })
    }
  
  
}
