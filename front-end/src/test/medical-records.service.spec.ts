import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {TestBed} from "@angular/core/testing";
import {MedicalRecordsService} from "../app/services/medical-records.service";
import {MedicalRecord} from "../app/models/MedicalRecord";

describe('MedicalRecordsService', () => {
  let service: MedicalRecordsService;
  let httpMock: HttpTestingController;
  const apiUrl = 'http://localhost:8080/medicalrecords';

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [MedicalRecordsService]
    });

    service = TestBed.inject(MedicalRecordsService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should fetch all notes for patient', () => {
    const mockNotes: MedicalRecord[] = [
      {
        patientId: '1',
        patient: 'John',
        note: 'first note'
      },
      {
        patientId: '1',
        patient: 'John',
        note: 'second note'
      }
    ];

    service.getMedicalRecords("1").subscribe(notes => {
      expect(notes.length).toBe(2);
      expect(notes).toEqual(mockNotes);
    });

    const req = httpMock.expectOne(apiUrl + '/1');
    expect(req.request.method).toBe('GET');
    req.flush(mockNotes);
  });

  it('should add a note', () => {
    const mockNote: MedicalRecord = {
      patientId: '1',
      patient: 'John',
      note: 'New note'
    };

    service.addMedicalRecord(mockNote).subscribe(note => {
      expect(note).toEqual(mockNote);
    });

    const req = httpMock.expectOne(`${apiUrl}`);
    expect(req.request.method).toBe('POST');
    req.flush(mockNote);
  });

});
