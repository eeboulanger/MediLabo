import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PatientService} from '../../services/patient.service';
import {Patient} from "../../models/Patient";
import {RouterLink} from "@angular/router";
import {RiskEvaluatorService} from "../../services/risk-evaluator.service";

@Component({
  selector: 'app-patient',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {
  patients: Patient[] = [];

  constructor(private patientService: PatientService, private riskEvaluatorService: RiskEvaluatorService) {
  }

  ngOnInit(): void {
    this.patientService.getPatients().subscribe({
      next: data => {
        this.patients = data;
        this.patients.forEach(patient => this.fetchRiskLevel(patient))
        console.log(this.patients);
      },
      error: err => {
        console.error("Error fetching patient data")
      }
    });
  }

  fetchRiskLevel(patient: Patient) {
    this.riskEvaluatorService.getRiskLevel(patient.id).subscribe({
      next: risk => {
        console.log("Risk level: {} patient: {} ", risk, patient.id);
        patient.riskLevel = risk;
      },
      error: err => {
        console.error("Error fetching risk level for patient {}", patient.id);
        patient.riskLevel = "unknown";
      }
    })
  }
}
