import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators } from '@angular/forms';
import { SnackbarService } from '../../service/snackbar/snackbar.service';
import { AdminService } from '../../service/admin/admin.service';

@Component({
  selector: 'app-create-quiz',
  templateUrl: './create-quiz.component.html',
  styleUrls: ['./create-quiz.component.css']
})
export class CreateQuizComponent implements OnInit {
  quizForm: FormGroup;
  currentStep = 1;
  isLoading = false;

  constructor(private fb: FormBuilder, private adminService: AdminService, private snackBar: SnackbarService) {}

  ngOnInit() {
    this.quizForm = this.fb.group({
      title: ['', Validators.required],
      category: ['', Validators.required],
      difficultyLevel: ['', Validators.required],  // Added difficulty level
      quizTime: ['', Validators.required],
      totalMarks: ['', Validators.required],
      totalQuestion: ['', [Validators.required, Validators.min(1)]],
      questionDtoList: this.fb.array([]),  // questionDtoList is an array of questions
    });
  }

  // Getter for 'questionDtoList' form array
  get questionDtoList() {
    return (this.quizForm.get('questionDtoList') as FormArray);
  }

  // Handle change in number of questions
  onTotalQuestionsChange() {
    const totalQuestions = this.quizForm.get('totalQuestion').value;

    // Reset questionDtoList array
    this.clearQuestions();

    // Add the correct number of questions dynamically
    for (let i = 0; i < totalQuestions; i++) {
      this.addQuestion();
    }
  }

  // Add a question form group to the questionDtoList array
  addQuestion() {
    const questionFormGroup = this.fb.group({
      questionText: ['', Validators.required],
      marks: ['', Validators.required],  // Added marks for each question
      option1: ['', Validators.required],
      option2: ['', Validators.required],
      option3: ['', Validators.required],
      option4: ['', Validators.required],
      answer: ['', Validators.required], 
    });
    this.questionDtoList.push(questionFormGroup);
  }

  // Clear existing questions in questionDtoList
  clearQuestions() {
    while (this.questionDtoList.length !== 0) {
      this.questionDtoList.removeAt(0);
    }
  }

  // Navigate to the next step
  nextStep() {
    if (this.currentStep < 3) {
      this.currentStep++;
    }
  }

  // Navigate to the previous step
  previousStep() {
    if (this.currentStep > 1) {
      this.currentStep--;
    }
  }

  // Submit form
  onSubmit() {
    if (this.quizForm.valid) {
      const quizForm = this.quizForm.value;
      this.isLoading = true;

      // Prepare quiz data for API
      const quizData = {
        quizId: 0,  // Assuming quiz ID starts as 0, you can adjust this based on your backend logic
        title: quizForm.title,
        category: quizForm.category,
        totalMarks: quizForm.totalMarks,
        difficultyLevel: quizForm.difficultyLevel,
        quizTime: quizForm.quizTime,
        totalQuestion: quizForm.totalQuestion,
        questionDtoList: quizForm.questionDtoList.map((q: any) => ({
          questionId: null, 
          question: q.questionText,
          marks: q.marks,  
          option1: q.option1,
          option2: q.option2,
          option3: q.option3,
          option4: q.option4,
          answer: q.answer,  
        })),
      };

      // Call the service to create the quiz
      this.adminService.createQuiz(quizData).subscribe(res => {
        this.isLoading = false;
        this.snackBar.openSnackBar("Quiz Created successfully");
        this.quizForm.reset();  // Reset the form after submission
      }, error => {
        this.isLoading = false;
        this.snackBar.openSnackBar("Something went wrong");
      });
    }
  }
}
