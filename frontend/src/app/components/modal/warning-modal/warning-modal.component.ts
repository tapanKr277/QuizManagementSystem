import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-warning-modal',
  templateUrl: './warning-modal.component.html',
  styleUrl: './warning-modal.component.css'
})
export class WarningModalComponent {

  @Input() isWarningModalOpen: boolean = false;  
  @Input() title: string = '';  
  @Output() onCloseModal = new EventEmitter<boolean>(); 
  
    close() {
      this.isWarningModalOpen = false;
      this.onCloseModal.emit(false); 
    }
  
  
    continue(){
      this.onCloseModal.emit(false);
    }
}
