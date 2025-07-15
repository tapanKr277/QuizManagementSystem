import { Injectable } from '@angular/core';
import { environment } from '../../../enviroment';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, catchError, map, Observable, of, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  private baseUrl = `${environment.apiUrl}/auth`;

  isLoggedIn = new BehaviorSubject(false);
  isAdminLogin = new BehaviorSubject<boolean>(false);

  private accessToken: string  = null;
  private refreshToken: string = null;
  private username: string = null;
  private userId = null;
  private email = null;

  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any>{
    const loginDto = { "email": email, "password": password};
    return this.http.post(`${this.baseUrl}/login`,loginDto);
  }


  getRefreshToken(){
    return this.refreshToken;
  }

  validateToken() {
    const token = this.getToken();
    const email = this.getEmail();
    const tokenDto = { "token": token, "email": email };
    return this.http.post(`${this.baseUrl}/validate-token`, tokenDto );
  }


  resolveToken(): boolean {
    this.accessToken = localStorage.getItem('token');
    this.isLoggedIn.next(this.accessToken ?  true : false);
    return this.accessToken ? true : false;
  }

  setDataAfterLogin(data){
    localStorage.clear();
    this.accessToken = data['accessToken'];
    this.username = data['username']; 
    this.email = data['email'];
    this.refreshToken = data['refreshToken']
    this.userId = data['userId'];
    this.isLoggedIn.next(true);
    localStorage.setItem('userId', this.userId);
    localStorage.setItem('token', this.accessToken);
    localStorage.setItem('username', this.username);
    localStorage.setItem('email', this.email);
    localStorage.setItem('refreshToken', this.refreshToken);
    this.isAdminLogin.next(data['isAdminLogin']);
  }

  logout(){
    this.isLoggedIn.next(false);
    this.isAdminLogin.next(false);
    this.accessToken = null;
    this.userId = null;
    this.email = null;
    this.username = null;
    this.refreshToken = null;
    localStorage.clear();
  }

  getToken(): string {
    return this.accessToken;
  }

  getEmail(): string{
    return this.email;
  }

  hasToken(): boolean  {
    if(localStorage.getItem('token') && localStorage.getItem('username') && localStorage.getItem('email')){
      this.accessToken = localStorage.getItem('token');
      this.username = localStorage.getItem('username');
      this.email = localStorage.getItem('email');
      this.refreshToken = localStorage.getItem('refreshToken');
      return true;
    }
    return false;
  }

  getUsername(){
    return this.username;
  }
  

  contactUs(sender_name:string, sender_email: string, message: string){
    return this.http.post(`${this.baseUrl}/email/sendMail`, {"senderName":sender_name, "senderEmail":sender_email , message});
  }


  verifyToken(email, token){
    return this.http.get(`${this.baseUrl}/verify-email?token=${token}&email=${email}`);
  }


  updateRefreshTokenAndAccessToken(refreshToken: string,email: string){
    const data = {
      "refreshToken" : refreshToken,
      "email": email
    }
    return this.http.post(`${this.baseUrl}/refresh-token`, data);
  }

  get isAdminLogin$(){
    return this.isAdminLogin.asObservable();
  }

  setIsAdminLogin(status){
    this.isAdminLogin.next(status);
  }
}
