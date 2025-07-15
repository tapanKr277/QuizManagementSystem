import { Component } from '@angular/core';
import { QuizComponent } from '../quiz/quiz.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  constructor(private router: Router) {
    
  }

  onClick(category){
    this.router.navigate(['/quiz'], { queryParams: { category: category } });
  }

}
