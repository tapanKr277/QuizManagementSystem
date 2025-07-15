import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../enviroment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private baseUrl = `${environment.apiUrl}/auth/admin`;

  constructor(private http: HttpClient) { }

  
  // uploadPdf(formData): Observable<any> {
  //   return this.http.post(`${this.baseUrl}/upload-pdf`, formData);
  // }

  uploadPdf(file: File): Observable<any> {
    // Create a new FormData object
    const formData = new FormData();
  
    // Append the file to FormData with the key 'file'
    formData.append('file', file, file.name);
  
    // Send the POST request to the backend API
    return this.http.post(`${this.baseUrl}/upload-pdf`, formData);
  }
  

  createQuiz(quizForm): Observable<any> {
    return this.http.post(`${this.baseUrl}/create-quiz`, quizForm);
  }

  getAllUserList(): Observable<any>{
    return this.http.get(`${this.baseUrl}/get-all-user-list`);
  }

  getAllRoleList():Observable<any>{
    return this.http.get(`${this.baseUrl}/get-all-role-list`)
  }

  getAllTokenList():Observable<any>{
    return this.http.get(`${this.baseUrl}/get-all-token-list`)
  }

  getAllOtpList():Observable<any>{
    return this.http.get(`${this.baseUrl}/get-all-otp-list`)
  }

  getAllStudentList():Observable<any>{
    return this.http.get(`${this.baseUrl}/get-all-student-list`)
  }

  getAllQuizList():Observable<any>{
    return this.http.get(`${this.baseUrl}/get-all-quiz-list`)
  }
  
  getAllQuestionList():Observable<any>{
    return this.http.get(`${this.baseUrl}/get-all-question-list`)
  }

  deleteUser(userId): Observable<any>{
    return this.http.delete(`${this.baseUrl}/delete-user?userId=${userId}`);
  }

  deleteQuiz(quizId): Observable<any>{
    return this.http.delete(`${this.baseUrl}/delete-quiz?quiz_id=${quizId}`);
  }

}
