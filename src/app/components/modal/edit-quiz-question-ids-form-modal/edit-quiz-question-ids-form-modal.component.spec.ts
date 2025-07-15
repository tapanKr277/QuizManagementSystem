import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditQuizQuestionIdsFormModalComponent } from './edit-quiz-question-ids-form-modal.component';

describe('EditQuizQuestionIdsFormModalComponent', () => {
  let component: EditQuizQuestionIdsFormModalComponent;
  let fixture: ComponentFixture<EditQuizQuestionIdsFormModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditQuizQuestionIdsFormModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditQuizQuestionIdsFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
