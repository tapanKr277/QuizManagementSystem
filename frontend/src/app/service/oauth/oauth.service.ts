import { Injectable } from '@angular/core';
import { environment } from '../../../enviroment';

  
@Injectable({
  providedIn: 'root'
})
export class OauthService {

  constructor() {
    
  }
  

  signInWithGoogle() {
    const oauthUrl = `http://localhost:8080/oauth2/authorization/google`;
    window.location.href = oauthUrl;
  }

  signInWithGithub() {  
    const oauthUrl = `http://localhost:8080/oauth2/authorization/github`;
    window.location.href = oauthUrl;
  }

   
}
