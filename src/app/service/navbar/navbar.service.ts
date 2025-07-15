import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NavbarService {


  private isNavbarOpen = new BehaviorSubject(true);

  constructor() { }


  getNavbarStatus(){
    return this.isNavbarOpen.asObservable();
  }

  setNavbarStatus(status){
    this.isNavbarOpen.next(status);
  }

}
