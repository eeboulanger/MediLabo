import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { PatientService } from '../app/services/patient.service';
import {PatientEditComponent} from "../app/components/patient-edit/patient-edit.component";
import {ActivatedRoute} from "@angular/router";

describe('PatientEditComponent', () => {
  let component: PatientEditComponent;
  let fixture: ComponentFixture<PatientEditComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [PatientEditComponent, HttpClientTestingModule],
      providers: [PatientService,
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: {
                get: () => '1', // Mock ID parameter
              },
            },
          },
        },]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create patient-edit component', () => {
    expect(component).toBeDefined();
  });
});
