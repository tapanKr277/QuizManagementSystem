import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../enviroment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AiService {

  private baseUrl = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  chatWithBot(message: string): Observable<any>{
    return this.httpClient.get(`${this.baseUrl}/auth/user/chat-with-bot?message=${message}`);
  }

}
