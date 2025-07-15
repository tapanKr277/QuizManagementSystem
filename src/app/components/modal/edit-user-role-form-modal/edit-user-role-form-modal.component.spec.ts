import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditUserRoleFormModalComponent } from './edit-user-role-form-modal.component';

describe('EditUserRoleFormModalComponent', () => {
  let component: EditUserRoleFormModalComponent;
  let fixture: ComponentFixture<EditUserRoleFormModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditUserRoleFormModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditUserRoleFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
