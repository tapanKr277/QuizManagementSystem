import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditTokenFormModalComponent } from './edit-token-form-modal.component';

describe('EditTokenFormModalComponent', () => {
  let component: EditTokenFormModalComponent;
  let fixture: ComponentFixture<EditTokenFormModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditTokenFormModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditTokenFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
