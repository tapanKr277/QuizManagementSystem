import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { QuizService } from '../../service/quiz/quiz.service';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentService } from '../../service/student/student.service';
import { SnackbarService } from '../../service/snackbar/snackbar.service';
import { DOCUMENT } from '@angular/common'; 
import { NavbarService } from '../../service/navbar/navbar.service';

@Component({
  selector: 'app-exam',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.css']
})
export class ExamComponent implements OnInit, OnDestroy {

  isSubmit: boolean = false;
  isLoading: boolean = false;
  flagedQuestionList: boolean[] = [];
  questions = [];
  quiz = null;
  questionAnswers: {  } = {};
  userId: number = null;
  quizId: number = null;
  timer: number = 0; 
  timerInterval: any;
  updateTime: any;
  quizCompleted: boolean = false;
  currentQuestionIndex = 0;
  isQuizCompleted = false;
  isModalVisible: boolean = false;
  docElement: HTMLElement;
  isFullScreen: boolean = false;
  warningCount: number = 0;
  isWarningModalOpen = false;
  isSideNavOpen = false;
  tabName;


  constructor(
    private quizService: QuizService,
    private activatedRoute: ActivatedRoute, 
    private router: Router,
    private snackBar: SnackbarService,
    private navbarService: NavbarService,
    @Inject(DOCUMENT) private document: Document,
  ) {}


  toggleSidenav(){
    this.isSideNavOpen = !this.isSideNavOpen;
  }


  ngOnDestroy(): void {
    this.navbarService.setNavbarStatus(true);
    // Remove event listeners on component destroy to avoid memory leaks
    // document.removeEventListener('fullscreenchange', this.handleFullscreenChange.bind(this));
    // document.removeEventListener('webkitfullscreenchange', this.handleFullscreenChange.bind(this));
    // document.removeEventListener('mozfullscreenchange', this.handleFullscreenChange.bind(this));
    // document.removeEventListener('msfullscreenchange', this.handleFullscreenChange.bind(this));
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(param => {
      this.quizId = Number(param.get('quizId'));
    });
    if (this.quizId != null) {
      this.getQuizById(this.quizId);
    }
    if(localStorage.getItem("userId")){
      this.userId = Number(localStorage.getItem('userId'));
    }
    this.docElement = this.document.documentElement;

    // Listen for fullscreen changes
    // document.addEventListener('fullscreenchange', this.handleFullscreenChange.bind(this));
    // document.addEventListener('webkitfullscreenchange', this.handleFullscreenChange.bind(this));
    // document.addEventListener('mozfullscreenchange', this.handleFullscreenChange.bind(this));
    // document.addEventListener('msfullscreenchange', this.handleFullscreenChange.bind(this));
  }


  // handleFullscreenChange(): void {
  //   if (
  //     document.fullscreenElement || 
  //     document.webkitFullscreenElement || 
  //     document.mozFullScreenElement || 
  //     document.msFullscreenElement
  //   ) {
  //     this.isFullScreen = true;
  //   } else {
  //     this.isFullScreen = false;
  //     this.isWarningModalOpen = true;
  //     this.warningCount++;
  //     if(this.userId && this.quizId){
  //       this.quizService.updateWarningCount(this.warningCount, this.userId, this.quizId).subscribe(res=>{
  //         this.warningCount = res['warningCount'] | 0;
  //       }, error =>{
  //         console.log(error);
  //       });
  //     }
      
      

  //     if (this.warningCount >= 3) {
  //       this.submitQuiz(); // Automatically submit the quiz after the 2nd warning
  //     } 
    
  //   }
  // }

  // toggleFullScreen(): void {
  //   if (!this.isFullScreen) {
  //     // Enter fullscreen
  //     this.docElement.requestFullscreen().catch((err) => {
  //       console.error("Error trying to enter fullscreen mode:", err);
  //     });
  //     // Hide the navbar when fullscreen is entered
  //     this.navbarService.setNavbarStatus(false);
  //   } else {
  //     // Exit fullscreen and show the navbar again
  //     if (document.fullscreenElement) {
  //       document.exitFullscreen().catch((err) => {
  //         console.error("Error trying to exit fullscreen mode:", err);
  //       });
  //     }
  //   }
  //   // Toggle the fullscreen flag
  //   this.isFullScreen = !this.isFullScreen;
  // }


  // Start the exam and enter fullscreen automatically
  startExam(quizId: number, userId: number): void {
    this.isLoading = true;
    this.quizService.startQuiz(quizId, userId).subscribe(
      res => {
        // Start the timer
        this.warningCount = res.warningCount | 0;
        this.timer = (res.quizTime - res.timeTaken)*60 | 0;
        if(res?.answers.length>0){
          for (let value of res.answers) {
            this.questionAnswers[value.questionId] = value.answer
          }
        }
        this.startTimer();
        this.updateExamProgress();
        this.isLoading = false;
        this.navbarService.setNavbarStatus(false);
        // this.enterFullscreen(); 
      },
      error => {
        this.isLoading = false;
        this.router.navigate(['/profile']);
        this.snackBar.openSnackBar(error.error.error);
      }
    );
  }


  updateExamProgress(){
    this.updateTime = setInterval(()=>{
      if(this.isSubmit){
        clearInterval(this.updateTime);
      }else{
        this.selectAnswer("");
      }
    }, 1000*60);
  }

  // Function to enter fullscreen mode
  // enterFullscreen(): void {
  //   if (!this.isFullScreen) {
  //     this.docElement.requestFullscreen().catch((err) => {
  //       console.error("Error entering fullscreen mode:", err);
  //     });
  //     this.isFullScreen = true;
      
  //   }
  // }

  startTimer(): void {
    this.timerInterval = setInterval(() => {
      if (this.timer > 0) {
        this.timer--;
      } else {
        this.submitQuiz();
        clearInterval(this.timerInterval); 
      }
    }, 1000);
  }

  getQuizById(id: number): void {
    this.isLoading = true;
    this.quizService.getQuizById(id).subscribe(
      res => {
        this.quiz = res;
        this.flagedQuestionList = [];
        this.questions = res?.questionDtoList;
        this.timer = (res?.quizTime * 60) | 0; 
       
        this.flagedQuestionList = Array(this.questions.length).fill(false);
        if (this.userId != null) {
          this.isLoading = false;
          this.startExam(this.quizId, this.userId); // Start the exam once the quiz data is fetched
          
        }
      },
      err => {
        this.flagedQuestionList = [];
        this.isLoading = false;
        this.questions = [];
        this.router.navigate(['/quiz']);
        this.snackBar.openSnackBar(err.error.error);
      }
    );
  }

  onSubmit(): void {
    this.submitQuiz();
  }

  onFlag(index: number): void {
    if (index >= 0 && index < this.questions.length) {
      this.flagedQuestionList[index] = !this.flagedQuestionList[index];
    }
    console.log(this.flagedQuestionList);
  }

  openModal(): void {
    this.isModalVisible = true;
  }

  onCloseModal(event: boolean): void {
    this.isModalVisible = event;
    if (event) {
      this.submitQuiz();
      this.isModalVisible = false;
    }
  }

  onWarningModalClose(event: boolean): void {
    this.isWarningModalOpen = event;
    this.navbarService.setNavbarStatus(false);
    // this.enterFullscreen();
  }

  nextQuestion(): void {
    if (this.currentQuestionIndex < this.questions.length - 1) {
      this.currentQuestionIndex++;
    }
    this.updateSubmitButton();
  }

  goTo(i: number): void {
    if (i >= 0 && i < this.questions.length) {
      this.currentQuestionIndex = i;
    }
    this.updateSubmitButton();
  }

  previousQuestion(): void {
    if (this.currentQuestionIndex > 0) {
      this.currentQuestionIndex--;
    }
    this.updateSubmitButton();
  }

  updateSubmitButton(): void {
    if (this.currentQuestionIndex === this.questions.length - 1) {
      this.isSubmit = true;
    } else {
      this.isSubmit = false;
    }
  }

  submitQuiz(): void {
    this.isQuizCompleted = true;
    this.isLoading = true;
    this.quizService.submitQuiz(this.quizId, this.userId, this.questionAnswers).subscribe(res => {
      this.isLoading = false;
      // this.toggleFullScreen();
      this.router.navigate(['/profile']);
      clearInterval(this.timerInterval);
    }, err => {
      this.snackBar.openSnackBar(err.error.error);
      this.isLoading = false;
    })
  }

  selectAnswer(answer: string): void {
    const questionId = this.questions[this.currentQuestionIndex].questionId;
    this.questionAnswers[questionId] = answer;
    this.quizService.updateQuiz(this.quizId, this.userId, this.questionAnswers).subscribe((res) => {
      this.warningCount = res.warningCount | 0;
      this.timer = (res.quizTime - res.timeTaken)*60 | 0;
    }, (err) => {
      console.log(err);
    })
  }
}
