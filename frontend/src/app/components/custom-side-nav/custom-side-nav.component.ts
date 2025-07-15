import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-custom-side-nav',
  templateUrl: './custom-side-nav.component.html',
  styleUrl: './custom-side-nav.component.css'
})
export class CustomSideNavComponent {


  @Output() selectedTab = new EventEmitter();

  selectTab(name){
    this.selectedTab.emit(name);
  }
  
}
