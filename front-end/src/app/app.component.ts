import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PatientListComponent} from './components/patient-list/patient-list.component';
import {Router, RouterLink, RouterLinkActive, RouterOutlet} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "./security/auth.service";
import {catchError, finalize, of} from "rxjs";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, PatientListComponent, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private auth: AuthService) {
  }
  logout() {
    this.auth.logout();
  }
}



