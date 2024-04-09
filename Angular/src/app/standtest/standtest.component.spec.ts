import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StandtestComponent } from './standtest.component';

describe('StandtestComponent', () => {
  let component: StandtestComponent;
  let fixture: ComponentFixture<StandtestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StandtestComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StandtestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
