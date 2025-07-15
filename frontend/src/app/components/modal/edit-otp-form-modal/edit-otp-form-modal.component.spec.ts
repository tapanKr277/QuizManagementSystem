import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditOtpFormModalComponent } from './edit-otp-form-modal.component';

describe('EditOtpFormModalComponent', () => {
  let component: EditOtpFormModalComponent;
  let fixture: ComponentFixture<EditOtpFormModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditOtpFormModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditOtpFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
