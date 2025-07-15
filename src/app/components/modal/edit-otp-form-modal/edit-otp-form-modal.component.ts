import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ModalService } from '../../../service/modal/modal.service';

@Component({
  selector: 'app-edit-otp-form-modal',
  templateUrl: './edit-otp-form-modal.component.html',
  styleUrl: './edit-otp-form-modal.component.css'
})
export class EditOtpFormModalComponent implements OnInit {
  isOtpModalOpen: boolean = false;  
  @Input() selectedOtp: any;  
  
  constructor(private modalService: ModalService) {
  
  }
  ngOnInit(): void {
   this.modalService.otpModalStatus$.subscribe(status=>{
    this.isOtpModalOpen = status;
   })
  }

  saveChanges() {
    this.modalService.setOtpModalOpen(false);
  }

  close() {
    this.modalService.setOtpModalOpen(false);
  }
}
