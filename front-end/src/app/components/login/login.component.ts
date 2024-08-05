/*
 * Created by elisabetboulanger on 03/08/2024
*/

import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../security/auth.service";
import {NavigationEnd, Router} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";

@Component(
  {
    selector: "login",
    templateUrl: "login.component.html",
    styleUrls: ['login.component.css'],
    imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
  })

export class LoginComponent implements OnInit {

  credentials = {username: '', password: ''};
  errorMessage: string = '';
  authenticated: boolean = false;

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.authenticated = this.authService.isAuthenticated();
  }

  login() {
    this.authService.authenticate(this.credentials).subscribe({
      next: () => {
        this.router.navigateByUrl('/patients');
      },
      error: (err) => {
        this.errorMessage = err.message;
      }
    });
  }
}
