/*
 * Created by elisabetboulanger on 03/08/2024
*/

import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../../security/auth.service";

@Component(
  {
    selector: "home",
    templateUrl: "home.component.html",
    styleUrls: ['home.component.css'],
    imports: [],
    standalone: true
  })

export class HomeComponent {
  constructor(private authService: AuthService) {
  }
}

