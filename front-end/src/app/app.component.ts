import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PatientComponent} from './components/patient/patient.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, PatientComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string;

  constructor() {
    this.title = 'Patient information';
  }
}



