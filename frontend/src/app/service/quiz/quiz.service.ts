import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Quiz } from '../../model/quiz';
import { environment } from '../../../enviroment';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  

  private baseUrl = `${environment.apiUrl}/auth/user`;

  constructor(private http: HttpClient) { }


  updateWarningCount(warningCount: number, userId: number, quizId: number) {
    return this.http.get(`${this.baseUrl}/update-warning-count?warningCount=${warningCount}&userId=${userId}&quizId=${quizId}`);
  }


  getAllQuiz():Observable<any>{
    return this.http.get(`${environment.apiUrl}/quiz/get-only-quiz`);
  }

  getQuizById(id: number): Observable<any>{
    return this.http.get(`${this.baseUrl}/get-quiz-by-id?quiz_id=${id}`);
  }

  getQuizByCatgory(category: string): Observable<any>{
    return this.http.get(`${environment.apiUrl}/quiz/get-quiz-by-category?category=${category}`)
  }

  getQuizByDate(date): Observable<any>{
    return this.http.get(`${environment.apiUrl}/quiz/get-quiz-by-date?date=${date}`)
  }

  startQuiz(quizId, userId): Observable<any>{
    if(quizId!=null && userId!=null){
      return this.http.get(`${this.baseUrl}/create-quiz-attempt?quiz_id=${quizId}&user_id=${userId}`);
    } 
      return null;
  }

  updateQuiz(quizId, userId, studentQuizAttemptData): Observable<any> {

    const answers = [];

    for (let key in studentQuizAttemptData) {
      answers.push({"questionId": key, "answer": studentQuizAttemptData[key]});
    }

    console.log(answers);

    if (quizId != null && userId != null) {
      const QuizAttemptDto = {
        "quizAttemptId": {
          "userId": userId,
          "quizId": quizId
        },
        "answers" : answers
      };
      // Send the actual object, not a stringified one
      return this.http.post(`${this.baseUrl}/update-quiz-attempt`, QuizAttemptDto);
    }
    return null;
  }
  
  submitQuiz(quizId, userId, studentQuizAttemptData): Observable<any> {

    const answers = [];

    for (let key in studentQuizAttemptData) {
      answers.push({"questionId": key, "answer": studentQuizAttemptData[key]});
    }
    console.log(answers);

    if (quizId != null && userId != null) {
      const QuizAttemptDto = {
        "quizAttemptId": {
          "userId": userId,
          "quizId": quizId
        },
        "answers": answers
      };
      return this.http.post(`${this.baseUrl}/submit-quiz-attempt`, QuizAttemptDto);
    }
    return null;
  }
  

  getAllAttemptedQuizsByUser(userId) : Observable<any>{
    return this.http.get(`${this.baseUrl}/get-user-quiz-attempt-list?user_id=${userId}`);
  }

  resumeQuiz(quizId, userId){
    return this.http.get(`${this.baseUrl}/resume?user_id=${userId}&quiz_id=${quizId}`)
  }

  getQuizByLevel(level): Observable<any>{
    return this.http.get(`${this.baseUrl}.get-quiz-by-level?=level=${level}`);
  }
  
}
