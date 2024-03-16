import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservaIntermediaComponent } from './reserva-intermedia.component';

describe('ReservaIntermediaComponent', () => {
  let component: ReservaIntermediaComponent;
  let fixture: ComponentFixture<ReservaIntermediaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReservaIntermediaComponent]
    });
    fixture = TestBed.createComponent(ReservaIntermediaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
