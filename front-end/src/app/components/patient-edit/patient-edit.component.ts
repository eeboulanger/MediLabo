import {Component, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import {ActivatedRoute} from '@angular/router';
import {PatientService} from '../../services/patient.service';
import {FormsModule} from "@angular/forms";

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
  patientId: string="";
  patient: any;
  errorMessage?: string;
  submitted: boolean = false;

  constructor(private route: ActivatedRoute, private patientService: PatientService) {
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

  submitForm(patientForm: any) {
    if (patientForm.valid) {
      console.log('Form data:', this.patient);
      this.submitted = true;
    }
  }
}

