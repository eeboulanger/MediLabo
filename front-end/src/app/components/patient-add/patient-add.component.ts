/*
 * Created by elisabetboulanger on 02/08/2024
*/
import {Component, OnInit} from '@angular/core';
import {Patient} from "../../models/Patient";
import {CommonModule} from "@angular/common";
import {FormsModule, NgForm} from "@angular/forms";
import {PatientService} from "../../services/patient.service";
import {Router} from "@angular/router";
import {PatientFormComponent} from "../patient-form/patient-form.component";

@Component(
  {
    selector: "patient-add",
    templateUrl: "patient-add.component.html",
    styleUrls: ['patient-add.component.css'],
    imports: [
      CommonModule,
      FormsModule,
      PatientFormComponent
    ],
    standalone: true
  })

export class PatientAddComponent implements OnInit {
  patient: Patient = new Patient();
  errorMessage: string = "";

  constructor(
    private patientService: PatientService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
  }

  submitForm(patientForm: NgForm) {
    if (patientForm.valid) {
      this.patientService.addPatient(this.patient).subscribe({
        next: () => {
          console.log("Patient added succesfully");
          this.router.navigate(['/patients']);
        },
        error: (error) => {
          console.error("Failed to create patient");
          this.errorMessage = error;
        }
      });
    }
  }
}
