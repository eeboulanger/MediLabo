import {ComponentFixture, TestBed, waitForAsync} from '@angular/core/testing';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {PatientListComponent} from '../app/components/patient-list/patient-list.component';
import {PatientService} from '../app/services/patient.service';
import {ActivatedRoute} from "@angular/router";
import {of} from "rxjs";
import {RiskEvaluatorService} from "../app/services/risk-evaluator.service";

class MockPatientService {
  getPatients() {
    return of([
      {
        id: 1,
        firstName: 'John',
        lastName: 'Doe',
        gender: 'Male',
        birthDate: '1990-01-01',
        address: '123 Main St',
        phoneNumber: '555-555-5555'
      },
      {
        id: 2,
        firstName: 'Jane',
        lastName: 'Doe',
        gender: 'Female',
        birthDate: '1992-01-01',
        address: '456 Elm St',
        phoneNumber: '555-555-5556'
      }
    ]);
  }
}

class MockRiskEvaluatorService {
  getRiskLevel() {
    return 'None';
  }
}

describe('PatientListComponent', () => {
  let component: PatientListComponent;
  let fixture: ComponentFixture<PatientListComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [PatientListComponent, HttpClientTestingModule],
      providers: [
        {provide: PatientService, useClass: MockPatientService},
        {provide: RiskEvaluatorService, useClass: MockRiskEvaluatorService},
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {paramMap: {get: () => null}}, // Provide a basic mock for ActivatedRoute
          }
        }
      ]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create patient list component', () => {
    expect(component).toBeDefined();
  });
});
