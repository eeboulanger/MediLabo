import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { PatientService } from '../../services/patient.service';
import {PatientAddComponent} from "./patient-add.component";
import {ActivatedRoute} from "@angular/router";

describe('PatientAddComponent', () => {
  let component: PatientAddComponent;
  let fixture: ComponentFixture<PatientAddComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [PatientAddComponent, HttpClientTestingModule],
      providers: [PatientService,
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {paramMap: {get: () => null}}, // Provide a basic mock for ActivatedRoute
          }
        }]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create patient-add component', () => {
    expect(component).toBeDefined();
  });
});
