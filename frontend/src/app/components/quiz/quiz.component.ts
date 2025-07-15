import { Component, OnInit, OnDestroy } from '@angular/core';
import { QuizService } from '../../service/quiz/quiz.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalService } from '../../service/modal/modal.service';
import { AdminService } from '../../service/admin/admin.service';
import { SnackbarService } from '../../service/snackbar/snackbar.service';
import { AuthService } from '../../service/auth/auth.service';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit, OnDestroy {

  userId: string = null;
  isLoading: boolean = false;
  dropdownVisible = false;
  selectedCategory: string = 'All';  // Default to 'All' on init
  categories = [];  // Store all available categories for the dropdown
  quizs = [];
  filteredQuizzes = [];
  searchText = "";
  isDeleteModalOpen=false;
  title="";
  selectedQuiz;
  isAdminLogin = false;


  selectedLevel: string = 'All';
  selectedDate: string = '';
  difficultyLevels: string[] = ['All','Easy', 'Medium', 'Hard'];

  clearFilters() {
    this.searchText = '';
    this.selectedCategory = 'All';
    this.selectedLevel = 'All';
    this.selectedDate = '';
    this.filterQuizzes();
  }

  constructor(private quizService: QuizService, 
    private router: Router, 
    private authService: AuthService,
    private adminService: AdminService,
    private activatedRoute: ActivatedRoute,
    private modalService: ModalService,
    private snackBarService: SnackbarService) {}

  ngOnInit(): void {
    this.userId = localStorage.getItem('userId');

    // Retrieve category from URL query parameters
    this.activatedRoute.queryParams.subscribe(params => {
      this.selectedCategory = params['category'] || 'All'; // Default to 'All' if no category in URL
    });

    // Fetch quizzes based on the selected category
    if (this.selectedCategory !== 'All') {
      this.getQuizByCategory(this.selectedCategory);
    } else {
      this.getAllQuizs();
    }

    this.modalService.deleteModalStatus$.subscribe(status=>{
      this.isDeleteModalOpen = status;
    })

    this.authService.isAdminLogin$.subscribe(res=>{
      this.isAdminLogin = res;
    })
    
  }

  getAllQuizs() {
    this.isLoading = true;
    this.quizService.getAllQuiz().subscribe((res) => {
      this.quizs = res;
      this.categories = [...new Set(res.map(quiz => quiz.category))]; // Ensure unique categories
      this.categories.push("All");
      this.filteredQuizzes = res;
      this.isLoading = false;
    }, (error) => {
      this.isLoading = false;
      console.error('Error fetching quizzes:', error);
    });
  }

  getQuizByCategory(category: string) {
    this.isLoading = true;
    if(category=="All"){
      this.getAllQuizs();
    }
    else{
      this.quizService.getQuizByCatgory(category).subscribe((res) => {
        this.quizs = res;
        this.filteredQuizzes = res;
        this.isLoading = false;
      }, (error) => {
        this.isLoading = false;
        console.error('Error fetching quizzes by category:', error);
      });
    }
    
  }

  filterQuizzes() {
    if (this.searchText.trim() === "") {
      this.filteredQuizzes = this.quizs;
    } else {
      this.filteredQuizzes = this.quizs.filter(quiz =>
        quiz.title.toLowerCase().includes(this.searchText.toLowerCase())
      );
    }
  }

  filterQuizzesByLevel(level){
    this.filteredQuizzes = this.quizs.filter(quiz=> quiz.difficultyLevel.toLowerCase().includes(level.toLowerCase()));
  }

  filterQuizzesByDate(date){
    this.isLoading = true;
    this.quizService.getQuizByDate(date).subscribe(res=>{
      this.quizs = res;
      this.filteredQuizzes = res;
      this.isLoading = false;
    }, error=>{
      this.quizs = [];
      this.filteredQuizzes = [];
      console.log(error);
    })
  }

  // Toggle dropdown visibility
  toggleDropdown() {
    this.dropdownVisible = !this.dropdownVisible;
  }

  // Update selected option and fetch quizzes by category
  selectOption(option: string) {
    this.selectedCategory = option;
    this.dropdownVisible = false;

    // Update URL query params and fetch quizzes based on the selected category
    this.router.navigate([], {
      relativeTo: this.activatedRoute,
      queryParams: { category: option === 'All' ? null : option },
      queryParamsHandling: 'merge'  // This ensures other query params are not lost
    });

    if (option !== 'All') {
      this.getQuizByCategory(option);
    } else {
      this.getAllQuizs();
    }
  }

  ngOnDestroy(): void {
    // Clean up any resources when the component is destroyed
  }

  onDelete(id, title){
    this.selectedQuiz = id;
    this.modalService.setDeleteModalOpen(true);
  }

  isDelete(event){
    this.isLoading = true;
    if(event){
      this.adminService.deleteQuiz(this.selectedQuiz).subscribe(res=>{
        this.getAllQuizs();
        this.isLoading = true;
        this.snackBarService.openSnackBar("Quiz Deleted Successfully");
      }, error=>{
        this.snackBarService.openSnackBar("Something went wrong!");
        this.isLoading = false;
        console.log(error);
      })
    }
  }

}
