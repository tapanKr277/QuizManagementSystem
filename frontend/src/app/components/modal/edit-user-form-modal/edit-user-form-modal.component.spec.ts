import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditUserFormModalComponent } from './edit-user-form-modal.component';

describe('EditUserFormModalComponent', () => {
  let component: EditUserFormModalComponent;
  let fixture: ComponentFixture<EditUserFormModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditUserFormModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditUserFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
