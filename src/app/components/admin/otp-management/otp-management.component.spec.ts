import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OtpManagementComponent } from './otp-management.component';

describe('OtpManagementComponent', () => {
  let component: OtpManagementComponent;
  let fixture: ComponentFixture<OtpManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OtpManagementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OtpManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
