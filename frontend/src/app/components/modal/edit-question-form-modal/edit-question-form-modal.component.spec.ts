import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditQuestionFormModalComponent } from './edit-question-form-modal.component';

describe('EditQuestionFormModalComponent', () => {
  let component: EditQuestionFormModalComponent;
  let fixture: ComponentFixture<EditQuestionFormModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditQuestionFormModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditQuestionFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
