import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})

export class RiskEvaluatorService {
  private apiUrl = 'http://localhost:8080/risk-evaluator'; //Gateway url

  constructor(private http: HttpClient) {
  }


  getRiskLevel(patientId: string): Observable<string> {
    return this.http.get(`${this.apiUrl}/${patientId}`, {responseType: 'text'}) // Specify responseType as 'text'
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    console.log("Error in risk evaluator service: " + error.message)
    return throwError(() => new Error(error.message));
  }
}
