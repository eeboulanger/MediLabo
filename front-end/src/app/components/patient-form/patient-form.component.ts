/*
 * Created by elisabetboulanger on 02/08/2024
*/
import {Component, EventEmitter, Input, Output} from '@angular/core';
import {FormsModule, NgForm} from "@angular/forms";
import {Patient} from "../../models/Patient";
import {NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";

@Component({
  selector: "patient-form",
  templateUrl: "patient-form.component.html",
  styleUrls: ["patient-form.component.css"],
  standalone: true,
  imports: [FormsModule, NgIf, RouterLink]
})

export class PatientFormComponent {
  @Input() patient: Patient = new Patient();
  @Input() errorMessage?: string;
  @Output() submitFormEvent = new EventEmitter<NgForm>();
  @Input() isEdit: boolean = false;
  submitted: boolean = false;

  submitForm(patientForm: NgForm) {
    this.submitted = true;
    if (patientForm.valid) {
      if (patientForm.valid) {
        this.submitFormEvent.emit(patientForm);
      } else {
        this.errorMessage = 'Please fill out the form correctly before submitting.';
      }
    }
  }
}
