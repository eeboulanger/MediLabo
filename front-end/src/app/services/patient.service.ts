import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Patient} from "../models/Patient";
import {catchError, Observable, throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private apiUrl = 'http://localhost:8080/patients'; //Gateway url

  constructor(private http: HttpClient) {
  }

  getPatients(): Observable<Patient[]> {
    return this.http.get<Patient[]>(this.apiUrl)
      .pipe(
        catchError(this.handleError)
      );
  }

  getPatientById(id: string): Observable<Patient> {
    return this.http.get<Patient>(`${this.apiUrl}/${id}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  updatePatient(patient: Patient, id: string) {
    return this.http.put<Patient>(`${this.apiUrl}/${id}`, patient)
      .pipe(
        catchError(this.handleError)
      );
  }

  addPatient(patient: Patient) {
    return this.http.post<Patient>(this.apiUrl, patient)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.headers) {
      error.headers.keys().forEach(key => {
        console.log(`Response Header: ${key} = ${error.headers.get(key)}`);
      });
    }
    if (error.status === 404) {
      errorMessage = 'Patient not found';
    } else {
      console.log("Error in patient service")
      errorMessage = 'An error occurred: ' + error.message;
    }
    return throwError(() => new Error(errorMessage));
  }
}
