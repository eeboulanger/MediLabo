import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {PatientService} from '../app/services/patient.service';
import {Patient} from '../app/models/Patient';

describe('PatientService', () => {
  let service: PatientService;
  let httpMock: HttpTestingController;
  const apiUrl = 'http://localhost:8080/patients';

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [PatientService]
    });

    service = TestBed.inject(PatientService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should fetch all patients', () => {
    const mockPatients: Patient[] = [
      {
        id: '1',
        firstName: 'John',
        lastName: 'Doe',
        gender: 'M',
        birthdate: '1990-01-01',
        address: '123 Main St',
        phoneNumber: '555-5555'
      },
      {
        id: '2',
        firstName: 'Jane',
        lastName: 'Doe',
        gender: 'F',
        birthdate: '1985-02-02',
        address: '456 Elm St',
        phoneNumber: '555-1234'
      }
    ];

    service.getPatients().subscribe(patients => {
      expect(patients.length).toBe(2);
      expect(patients).toEqual(mockPatients);
    });

    const req = httpMock.expectOne(apiUrl);
    expect(req.request.method).toBe('GET');
    req.flush(mockPatients);
  });

  it('should fetch a single patient by ID', () => {
    const mockPatient: Patient = {
      id: '1',
      firstName: 'John',
      lastName: 'Doe',
      gender: 'M',
      birthdate: '1990-01-01',
      address: '123 Main St',
      phoneNumber: '555-5555'
    };

    service.getPatientById('1').subscribe(patient => {
      expect(patient).toEqual(mockPatient);
    });

    const req = httpMock.expectOne(`${apiUrl}/1`);
    expect(req.request.method).toBe('GET');
    req.flush(mockPatient);
  });

  it('should update a patient', () => {
    const mockPatient: Patient = {
      id: '1',
      firstName: 'John',
      lastName: 'Doe',
      gender: 'M',
      birthdate: '1990-01-01',
      address: '123 Main St',
      phoneNumber: '555-5555'
    };

    service.updatePatient(mockPatient, '1').subscribe(patient => {
      expect(patient).toEqual(mockPatient);
    });

    const req = httpMock.expectOne(`${apiUrl}/1`);
    expect(req.request.method).toBe('PUT');
    req.flush(mockPatient);
  });

  it('should add a patient', () => {
    const mockPatient: Patient = {
      id: "null",
      firstName: 'John',
      lastName: 'Doe',
      gender: 'M',
      birthdate: '1990-01-01',
      address: '123 Main St',
      phoneNumber: '555-5555'
    };

    service.addPatient(mockPatient).subscribe(patient => {
      expect(patient).toEqual(mockPatient);
    });

    const req = httpMock.expectOne(`${apiUrl}`);
    expect(req.request.method).toBe('POST');
    req.flush(mockPatient);
  });

  it('should handle 404 error for getPatientById', () => {
    const errorMessage = 'Patient not found';

    service.getPatientById('1').subscribe(
      () => fail('Expected an error, not a patient'),
      error => {
        expect(error.message).toBe(errorMessage);
      }
    );

    const req = httpMock.expectOne(`${apiUrl}/1`);
    expect(req.request.method).toBe('GET');
    req.flush({message: errorMessage}, {status: 404, statusText: 'Not Found'});
  });

});
