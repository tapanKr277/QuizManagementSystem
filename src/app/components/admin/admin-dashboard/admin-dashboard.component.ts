import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../../service/admin/admin.service';
import { SidebarService } from '../../../service/sidebar/sidebar.service';
import { ModalService } from '../../../service/modal/modal.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent implements OnInit {


  selectedTab : string = "user";
  isSidenavOpen: boolean = false;





  constructor(private sidebarService: SidebarService, private modalService: ModalService) {
    
  }
  
  ngOnInit(): void {
    this.sidebarService.getSidebarStatus().subscribe(status=>{
      this.isSidenavOpen = status;
    })
  }

  
  selectTab(tabName){
    this.selectedTab = tabName;
   
  }

  isCreateFormEnable(formName: string): void {
    switch (formName) {
      case 'user':
        this.modalService.setUserModalOpen(true); // Open user creation modal
        break;
      case 'role':
        this.modalService.setRoleModalOpen(true); // Open role creation modal
        break;
      case 'token':
        this.modalService.setEmailTokenModalOpen(true); // Open email token creation modal
        break;
      case 'otp':
        this.modalService.setOtpModalOpen(true); // Open OTP creation modal
        break;
      case 'quiz':
        this.modalService.setQuizModalOpen(true); // Open quiz creation modal
        break;
      case 'question':
        this.modalService.setQuestionModalOpen(true); // Open question creation modal
        break;
      // Add more cases if needed for other sections
      default:
        break;
    }
    
  }




}
