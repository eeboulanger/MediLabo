import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PatientService} from '../../services/patient.service';
import {Patient} from "../../models/Patient";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-patient',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {
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
