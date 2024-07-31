import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PatientService} from '../../services/patient.service';
import {Patient} from "../../models/Patient";

@Component({
  selector: 'app-patient',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {
  patients: Patient[] = [];

  constructor(private patientService: PatientService) {
  }

  ngOnInit(): void {
    this.patientService.getPatients().subscribe({
      next: data => {
        this.patients = data;
        console.log(this.patients);
      }
    });
  }
}
