import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditStudentFormModalComponent } from './edit-student-form-modal.component';

describe('EditStudentFormModalComponent', () => {
  let component: EditStudentFormModalComponent;
  let fixture: ComponentFixture<EditStudentFormModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditStudentFormModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditStudentFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
