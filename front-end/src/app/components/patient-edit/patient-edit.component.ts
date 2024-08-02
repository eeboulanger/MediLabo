import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';
import {PatientService} from '../../services/patient.service';
import {FormsModule, NgForm} from "@angular/forms";
import {Patient} from "../../models/Patient";

@Component({
  selector: 'patient-edit',
  templateUrl: './patient-edit.component.html',
  standalone: true,
  styleUrls: ['./patient-edit.component.css'],
  imports: [
    CommonModule,
    FormsModule
  ],
})

export class PatientEditComponent implements OnInit {
  patientId: string = "";
  patient: Patient = new Patient();
  errorMessage?: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private patientService: PatientService
  ) {
  }

  ngOnInit(): void {
    this.patientId = this.route.snapshot.paramMap.get('id') || '';
    this.getPatient();
  }

  getPatient(): void {
    this.patientService.getPatientById(this.patientId).subscribe({
        next: (patient) => {
          this.patient = patient;
        },
        error: (error) => {
          this.errorMessage = error;
        }
      }
    );
  }

  submitForm(patientForm: NgForm) {
    if (patientForm.valid) {
      this.patientService.updatePatient(this.patient, this.patientId).subscribe({
        next: () => {
          console.log('Patient updated successfully!');
          this.router.navigate(['/patients']); // Redirect to patient list
        },
        error: (error) => {
          this.errorMessage = error;
        }
      });
    }
  }
}

