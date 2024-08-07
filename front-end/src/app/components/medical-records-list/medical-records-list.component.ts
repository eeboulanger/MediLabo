/*
 * Created by elisabetboulanger on 07/08/2024
*/

import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {MedicalRecord} from "../../models/MedicalRecord";
import {MedicalRecordService} from "../../services/medical-recordservice";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'medical-records-list',
  standalone: true,
  imports: [RouterLink, NgForOf],
  templateUrl: "medical-records-list.component.html",
  styleUrls: ['medical-records-list.component.css']
})

export class MedicalRecordsListComponent implements OnInit {
  patientId: string = '';
  medicalRecords: MedicalRecord[] | undefined;

  constructor(private recordService: MedicalRecordService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    this.patientId = this.route.snapshot.paramMap.get('id') || '';

    this.recordService.getMedicalRecords(this.patientId).subscribe({
      next: records => {
        this.medicalRecords = records;
      }
    })
  }
}

