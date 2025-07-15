import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-student-management',
  templateUrl: './student-management.component.html',
  styleUrl: './student-management.component.css'
})
export class StudentManagementComponent {
  @Input() students = [];

  openEditStudentModal(student){

  }

  deleteStudentRecord(id){
    
  }
}
