import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SidebarService {

  private sidebarStatus = new BehaviorSubject<boolean>(false);

  constructor() { }

  getSidebarStatus(){
    return this.sidebarStatus.asObservable();
  }

  setSidebarStatus(status: boolean) {
    this.sidebarStatus.next(status); 
  }
  
}
