import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-submit-modal',
  templateUrl: './submit-modal.component.html',
  styleUrl: './submit-modal.component.css'
})
export class SubmitModalComponent {
  
  @Input() isVisible: boolean = false;  
  @Input() title: string = '';  
  @Output() closeModal = new EventEmitter<boolean>(); 

  close() {
    this.isVisible = false;
    this.closeModal.emit(false); 
  }


  submit(){
    this.closeModal.emit(true);
  }
}
