import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ModalService } from '../../../service/modal/modal.service';

@Component({
  selector: 'app-delete-modal',
  templateUrl: './delete-modal.component.html',
  styleUrl: './delete-modal.component.css'
})
export class DeleteModalComponent implements OnInit{
  
    isDeleteModalOpen: boolean = false;  
    @Input() title: string = '';  
    @Output() isDelete = new EventEmitter<boolean>(false);

    constructor(private modalService: ModalService) {
      
    }
    ngOnInit(): void {
      this.modalService.deleteModalStatus$.subscribe(status=>{
        this.isDeleteModalOpen=status;
      })
    }
  
    no() {
      this.modalService.setDeleteModalOpen(false); 
    }

    yes(){
      this.isDelete.emit(true);
      this.modalService.setDeleteModalOpen(false); 
    }

}
