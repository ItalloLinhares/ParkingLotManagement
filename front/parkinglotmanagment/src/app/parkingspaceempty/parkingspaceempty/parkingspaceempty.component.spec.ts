import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParkingspaceemptyComponent } from './parkingspaceempty.component';

describe('ParkingspaceemptyComponent', () => {
  let component: ParkingspaceemptyComponent;
  let fixture: ComponentFixture<ParkingspaceemptyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParkingspaceemptyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParkingspaceemptyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
