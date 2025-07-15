import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ModalService } from '../../../service/modal/modal.service';

@Component({
  selector: 'app-edit-token-form-modal',
  templateUrl: './edit-token-form-modal.component.html',
  styleUrl: './edit-token-form-modal.component.css'
})
export class EditTokenFormModalComponent implements OnInit{

  isEmailTokenModalOpen: boolean = false;  
  @Input() selectedEmailToken: any; 
 

  constructor(private modalService: ModalService) {
    
  }

  ngOnInit() {
    // Format the dates to remove fractional seconds and use the proper format for `datetime-local`
    // this.selectedEmailToken.expiryTime = this.formatDate(this.selectedEmailToken.expiryTime);
    // this.selectedEmailToken.createdAt = this.formatDate(this.selectedEmailToken.createdAt);
    // this.selectedEmailToken.lastUpdate = this.formatDate(this.selectedEmailToken.lastUpdate);
    this.modalService.emailTokenModalStatus$.subscribe(status=>{
      this.isEmailTokenModalOpen = status;
    })
 
  }

  // formatDate(date: string): string {
  //   // Remove fractional seconds and format as YYYY-MM-DDTHH:mm (datetime-local format)
  //   const formattedDate = new Date(date).toISOString().slice(0, 16);
  //   return formattedDate;
  // }

  saveChanges() { 
    this.modalService.setEmailTokenModalOpen(false);
  }

  close() {
    this.modalService.setEmailTokenModalOpen(false);
  }
}
