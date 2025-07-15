import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ModalService } from '../../../service/modal/modal.service';

@Component({
  selector: 'app-edit-user-role-form-modal',
  templateUrl: './edit-user-role-form-modal.component.html',
  styleUrl: './edit-user-role-form-modal.component.css'
})
export class EditUserRoleFormModalComponent implements OnInit {

  isRoleModalOpen: boolean = false;
  @Input() selectedUserRole: any ;
  
  userRoleForm : FormGroup;
  
  constructor(private fb: FormBuilder, private modalService: ModalService) {
    this.userRoleForm = fb.group({
      
    })
  }
  ngOnInit(): void {
    this.modalService.roleModalStatus$.subscribe(status=>{
      this.isRoleModalOpen = status;
    })
  }

  saveChanges() {
    this.modalService.setRoleModalOpen(false);
  }

  // Close the modal
  close() {
    this.modalService.setRoleModalOpen(false);
  }
}
