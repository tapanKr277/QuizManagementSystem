import { Component, Input, OnInit } from '@angular/core';
import { AdminService } from '../../../service/admin/admin.service';
import { ModalService } from '../../../service/modal/modal.service';

@Component({
  selector: 'app-token-management',
  templateUrl: './token-management.component.html',
  styleUrl: './token-management.component.css'
})
export class TokenManagementComponent implements OnInit{
  tokens = [];
  isLoading = false;
  selectedEmailToken;
  isEmailTokenModalOpen = false;
  isDeleteModalOpen = false;
  title;

  constructor(private adminService: AdminService, private modalService: ModalService) {

  }

  ngOnInit(): void {
    this.getAllTokenList();
    this.modalService.emailTokenModalStatus$.subscribe(status=>{
      this.isEmailTokenModalOpen = status;
    })
    this.modalService.deleteModalStatus$.subscribe(status=>{
      this.isDeleteModalOpen = status;
    })
  }

  openEditTokenModal(token){
    this.selectedEmailToken = token;
    this.modalService.setEmailTokenModalOpen(true);
  }

  deleteTokenRecord(id){
    this.title = id;
    this.modalService.setDeleteModalOpen(true);
  }

  onCloseModal(){
    this.modalService.setEmailTokenModalOpen(false);
    this.modalService.setDeleteModalOpen(false);
  }

  getAllTokenList(){
    this.isLoading = true;
    this.adminService.getAllTokenList().subscribe(res=>{
      this.tokens = res;
      this.isLoading = false;
    },err=>{
      console.log(err);
      this.isLoading = false;
    })

  }
}
