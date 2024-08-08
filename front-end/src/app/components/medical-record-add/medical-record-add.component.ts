/*
 * Created by elisabetboulanger on 08/08/2024
*/

import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {MedicalRecordsService} from "../../services/medical-records.service";
import {MedicalRecord} from "../../models/MedicalRecord";
import {FormsModule, NgForm} from "@angular/forms";
import {NgIf} from "@angular/common";

@Component({
  selector: "record-add",
  standalone: true,
  templateUrl: "medical-record-add.component.html",
  imports: [
    NgIf,
    FormsModule,
    RouterLink
  ],
  styleUrl: "medical-record-add.component.css"
})

export class MedicalRecordsAddComponent implements OnInit {

  medicalRecord: MedicalRecord = new MedicalRecord('', '', '');
  patientId: string = '';
  patientName: string = '';
  errorMessage: string = '';

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private service: MedicalRecordsService) {
  }

  /**
   * Get patient id and name from route and initialize a new medical record with the data
   */
  ngOnInit(): void {
    this.patientId = this.route.snapshot.paramMap.get('id') || '';
    this.patientName = this.route.snapshot.queryParamMap.get('patientName') || '';
    this.medicalRecord = new MedicalRecord(this.patientId, this.patientName, '');
  }

  /**
   * Save new medical record with note from form and navigate back to the patient's notes
   * @param form
   */
  submitForm(form: NgForm) {
    if (form.valid) {
      this.service.addMedicalRecord(this.medicalRecord).subscribe({
          next: () => {
            console.log('Medical record added successfully');
            this.router.navigate(["/medical-records", this.patientId]);
          },
          error: (error) => {
            console.error("Failed to create medical record");
            this.errorMessage = error;
          }
        }
      );
    }
  }
}
