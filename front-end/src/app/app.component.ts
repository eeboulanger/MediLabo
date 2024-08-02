import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PatientListComponent} from './components/patient-list/patient-list.component';
import {RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, PatientListComponent, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string;

  constructor() {
    this.title = 'Patient information';
  }
}



