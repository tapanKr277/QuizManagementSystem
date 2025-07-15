import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../../service/admin/admin.service';
import { ModalService } from '../../../service/modal/modal.service';
import { SnackbarService } from '../../../service/snackbar/snackbar.service';

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrl: './user-management.component.css'
})
export class UserManagementComponent implements OnInit {

  isLoading = false;
  users = [];
  isUserModalOpen = false;
  isDeleteModalOpen = false;
  selectedUser;
  title = "";


  onCloseModal(){
    this.modalService.setUserModalOpen(false);
    this.modalService.setDeleteModalOpen(false);
    
  }

  isDelete(event){
    console.log(this.selectedUser);
    if(event && this.selectedUser.userId!=null){
      this.isLoading = true;
      this.adminService.deleteUser(this.selectedUser.userId).subscribe(res=>{
        this.snackBar.openSnackBar("User deleted successfully");
        this.isLoading = false;
      }, error =>{
        this.isLoading = false;
        this.snackBar.openSnackBar(error.error.error);
      })
    }
  }


  onUserUpdated(event) {
    if(event){
      this.getAllUserList();  // Refetch the user list after an update
    }
  }

  constructor(private adminService: AdminService,private modalService: ModalService, private snackBar : SnackbarService) {
    
  }

  ngOnInit(): void {
    this.getAllUserList();
    this.modalService.userModalStatus$.subscribe(status=>{
      this.isUserModalOpen = status;
    })
    this.modalService.deleteModalStatus$.subscribe(status=>{
      this.isDeleteModalOpen = status;
    })
  }


  getAllUserList(){
    this.isLoading = true;
    this.adminService.getAllUserList().subscribe(res=>{
      this.users = res;
      this.isLoading = false;
    }, err=>{
      this.isLoading = false;
      console.log(err);
    })
  }
  
 
  
  openEditModal(user){
    this.modalService.setUserModalOpen(true);
    this.selectedUser=user;
  }

  deleteRecord(title){
    this.title = title;
    this.modalService.setDeleteModalOpen(true);
  }

}
