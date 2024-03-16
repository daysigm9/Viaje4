import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GeneraPdfComponent } from './genera-pdf.component';

describe('GeneraPdfComponent', () => {
  let component: GeneraPdfComponent;
  let fixture: ComponentFixture<GeneraPdfComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GeneraPdfComponent]
    });
    fixture = TestBed.createComponent(GeneraPdfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
