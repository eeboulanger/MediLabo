import {ComponentFixture, TestBed, waitForAsync} from '@angular/core/testing';
import {PatientFormComponent} from '../app/components/patient-form/patient-form.component';
import {ActivatedRoute} from "@angular/router";

describe('PatientFormComponent', () => {
  let component: PatientFormComponent;
  let fixture: ComponentFixture<PatientFormComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [PatientFormComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {paramMap: {get: () => null}}, // Provide a basic mock for ActivatedRoute
          }
        }]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create patient form component', () => {
    expect(component).toBeDefined();
  });
});
