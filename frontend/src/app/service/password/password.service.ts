import { Injectable } from '@angular/core';
import { environment } from '../../../enviroment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PasswordService {

  private baseUrl = `${environment.apiUrl}/auth`

  constructor(private http: HttpClient) { }

  forgotPassword(email:string): Observable<any>{
    return this.http.get(`${this.baseUrl}/forgot-password?email=${email}`);
  }


  verifyOtp(email: string, otp: string){
    return this.http.post(`${this.baseUrl}/verify-otp`, {email, otp});
  }

  reSendOtp(email: string){
    return this.http.get(`${this.baseUrl}/resend-otp?email=${email}`);
  }

  changePassword(email:string, password: string, confirm_password: string){
    const body =  {
      "oldPassword": "",
      "email": email,
      "password": password,
      "confirmPassword": confirm_password
    };
    return this.http.post(`${this.baseUrl}/change-password`, body);
  }
}
