import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewCostomerComponent } from './new-costomer.component';

describe('NewCostomerComponent', () => {
  let component: NewCostomerComponent;
  let fixture: ComponentFixture<NewCostomerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NewCostomerComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NewCostomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
