import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {MedicalRecord} from "../models/MedicalRecord";
import {catchError, Observable, throwError} from "rxjs";

@Injectable({
  providedIn: "root"
})

export class MedicalRecordService {

  private apiUrl = 'http://localhost:8080/medicalrecords'; //Gateway url

  constructor(private http: HttpClient) {
  }

  getMedicalRecords(patientId: string): Observable<MedicalRecord[]> {
    return this.http.get<MedicalRecord[]>(`${this.apiUrl}/${patientId}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    console.log("Error in patient service")
    errorMessage = 'An error occurred: ' + error.message;
    return throwError(() => new Error(errorMessage));
  }
}
