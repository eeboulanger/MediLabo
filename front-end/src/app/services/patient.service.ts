import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Patient} from "../models/Patient";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private apiUrl = 'http://localhost:8080/patients'; //Gateway url

  constructor(private http: HttpClient) { }

  getPatients(): Observable<Patient[]> {
    return this.http.get<Patient[]>(this.apiUrl);
  }
}
