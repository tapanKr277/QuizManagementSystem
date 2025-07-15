import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditQuizFormModalComponent } from './edit-quiz-form-modal.component';

describe('EditQuizFormModalComponent', () => {
  let component: EditQuizFormModalComponent;
  let fixture: ComponentFixture<EditQuizFormModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditQuizFormModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditQuizFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
