import { Component } from '@angular/core';
import { SnackbarService } from '../../../service/snackbar/snackbar.service';
import { Router } from '@angular/router';
import { AdminService } from '../../../service/admin/admin.service';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-upload-pdf',
  templateUrl: './upload-pdf.component.html',
  styleUrl: './upload-pdf.component.css'
})
export class UploadPdfComponent {
  isLoading = false;
  selectedFile: File | null = null;
  quiz: any = null;
  questions: any[] = [];
  quizForm: FormGroup;


  ngOnInit() {
    this.quizForm = this.fb.group({
      title: ['', Validators.required],
      category: ['', Validators.required],
      difficultyLevel: ['', Validators.required],
      quizTime: ['', Validators.required],
      totalMarks: ['', Validators.required],
      totalQuestion: ['', [Validators.required, Validators.min(1)]],
      questionDtoList: this.fb.array([]),
    });
  }

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

  get questionDtoList() {
    return (this.quizForm.get('questionDtoList') as FormArray);
  }

  clearQuestions() {
    while (this.questionDtoList.length !== 0) {
      this.questionDtoList.removeAt(0);
    }
  }

  onTotalQuestionsChange() {
    const totalQuestions = this.quizForm.get('totalQuestion').value;

    // Reset questionDtoList array
    this.clearQuestions();

    // Add the correct number of questions dynamically
    for (let i = 0; i < totalQuestions; i++) {
      this.addQuestion();
    }
  }

  constructor(private fb: FormBuilder, private adminService: AdminService, private snackBar: SnackbarService, private router: Router) { }

  // Handle file selection
  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files[0]) {
      this.selectedFile = input.files[0];
    }
  }

  // Handle form submission and file upload
  onSubmit() {
    if (!this.selectedFile) {
      return;
    }

    const formData = new FormData();
    this.isLoading = true;
    formData.append('pdf', this.selectedFile, this.selectedFile.name);

    this.adminService.uploadPdf(this.selectedFile).subscribe(res => {
      this.isLoading = false;
      this.quiz = res; // Receive the response from the API
      this.questions = res.questionDtoList; // Update questions data
      this.patchValue(res); // Populate the form with received data
    }, error => {
      this.isLoading = false;
      this.snackBar.openSnackBar("Something went wrong");
      console.log(error);
      this.quiz = null;
      this.questions = [];
    });
  }


  patchValue(data) {
    // Populate the main quiz fields
    this.quizForm.patchValue({
      title: data.title,
      category: data.category,
      difficultyLevel: data.difficultyLevel,
      quizTime: data.quizTime,
      totalMarks: data.totalMarks,
      totalQuestion: data.questionDtoList.length, // Set the total number of questions
    });
    this.clearQuestions();

    // Add each question dynamically to the questionDtoList form array
    data.questionDtoList.forEach(question => {
      const questionFormGroup = this.fb.group({
        questionText: [question.question, Validators.required],
        marks: [question.marks, Validators.required],
        option1: [question.option1, Validators.required],
        option2: [question.option2, Validators.required],
        option3: [question.option3, Validators.required],
        option4: [question.option4, Validators.required],
        answer: [question.answer, Validators.required],
      });

      this.questionDtoList.push(questionFormGroup);
    });
  }

  generateQuiz() {
    if (this.quizForm.valid) {
      this.isLoading = true;
      const quizForm = this.quizForm.value;
      const quizData = {
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
      this.adminService.createQuiz(quizData).subscribe(res => {
        this.isLoading = false;
        this.router.navigate(["/quiz"]);
        this.snackBar.openSnackBar("Quiz Create successfully");
      }, error => {
        this.isLoading = false;
        this.snackBar.openSnackBar("Something went wrong!");
      })
    }
    else {
      console.log("invalid form data");
    }

  }

}
