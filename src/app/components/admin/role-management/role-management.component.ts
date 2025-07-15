import { Component, Input, OnInit } from '@angular/core';
import { AdminService } from '../../../service/admin/admin.service';
import { ModalService } from '../../../service/modal/modal.service';

@Component({
  selector: 'app-role-management',
  templateUrl: './role-management.component.html',
  styleUrl: './role-management.component.css'
})
export class RoleManagementComponent implements OnInit{
  roles = [];
  isLoading = false;
  isDeleteModalOpen = false;
  title = "";
  isRoleModalOpen = false;
  selectedUserRole;

  constructor(private adminService: AdminService, private modalService: ModalService) {
    
  }
  ngOnInit(): void {
    this.getAllRoleList();
    this.modalService.deleteModalStatus$.subscribe(status=>{
      this.isDeleteModalOpen = status;
    })
    this.modalService.roleModalStatus$.subscribe(status=>{
      this.isRoleModalOpen = status;
    })
  }

  openEditRoleModal(role){
    this.selectedUserRole = role;
    this.modalService.setRoleModalOpen(true);
  }

  deleteRoleRecord(id){
    this.title = id;
    this.modalService.setDeleteModalOpen(true);
  }

  onCloseModal(){
    this.modalService.setRoleModalOpen(false);
    this.modalService.setDeleteModalOpen(false);
  }

  getAllRoleList(){
    this.isLoading = true;
    this.adminService.getAllRoleList().subscribe(res=>{
      this.roles = res;
      this.isLoading = false;
    }, err=> {
      console.log(err);
      this.isLoading = false;
    })
  }
}
