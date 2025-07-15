import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../enviroment';
import { UserModel } from '../../model/usermodel';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  private baseUrl = `${environment.apiUrl}`;

  constructor(private http:HttpClient) { }

  createUser(userdata) : Observable<any>{
    return this.http.post(`${this.baseUrl}/auth/add-user`, userdata);
  }

  getUserById(userId){
    return this.http.get(`${this.baseUrl}/auth/user/get-user-id?user_id=${userId}`)
  }
  
  updateUserData(userData, userId){
    const data = {
      'userId': userId,
      'firstName': userData.firstName,
      'lastName': userData.lastName,
      'phoneNumber': userData.phoneNumber
    }
    return this.http.post(`${this.baseUrl}/auth/user/update-user-data`, data);
  }

  getUserNumberOfQuizAttemptMonthWise(userId, year){
    return this.http.get(`${this.baseUrl}/auth/user/get-user-number-of-quiz-attempt-month-wise?userId=${userId}&year=${year}`);
  }

  updateUserDataAdmin(userData){
    const data = {
      'userId': userData.userId,
      'username': userData.username,
      'firstName': userData.firstName,
      'lastName': userData.lastName,
      'email': userData.email,
      'phoneNumber': userData.phoneNumber,
      'isOtpVerified': userData.isOtpVerified,
      'isVerified': userData.isVerified,
      'isActive': userData.isActive,
    }
    return this.http.post(`${this.baseUrl}/auth/admin/update-user`, data);
  }
  
}
