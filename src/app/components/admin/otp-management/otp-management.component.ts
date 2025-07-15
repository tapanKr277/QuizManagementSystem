import { Component, Input, OnInit } from '@angular/core';
import { AdminService } from '../../../service/admin/admin.service';
import { ModalService } from '../../../service/modal/modal.service';

@Component({
  selector: 'app-otp-management',
  templateUrl: './otp-management.component.html',
  styleUrl: './otp-management.component.css'
})
export class OtpManagementComponent implements OnInit{
  otps = [];

  isLoading = false;
  isDeleteModalOpen = false;
  isOtpModalOpen = false;
  selectedOtp;
  title = "";

  openEditOtpModal(otp){
    this.selectedOtp = otp;
    this.modalSerivce.setOtpModalOpen(true);
  }


  deleteOtpRecord(id){
    this.title = id;
    this.modalSerivce.setDeleteModalOpen(true);
  }

  onCloseModal(){
   this.modalSerivce.setDeleteModalOpen(false);
   this.modalSerivce.setOtpModalOpen(false);
  }

  constructor(private adminService: AdminService, private modalSerivce: ModalService) {
    
  }

  ngOnInit(): void {
    this.getAllOtpList();
    this.modalSerivce.otpModalStatus$.subscribe(status=>{
      this.isOtpModalOpen = status;
    })
    this.modalSerivce.deleteModalStatus$.subscribe(status=>{
      this.isDeleteModalOpen = status;
    })
  }

  getAllOtpList(){
    this.isLoading = true;
    this.adminService.getAllOtpList().subscribe(res=>{
      this.otps = res;
      this.isLoading = false;
    }, err =>{
      console.log(err);
      this.isLoading = false;
    })
  }
}
