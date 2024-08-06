import {ComponentFixture, TestBed} from '@angular/core/testing';
import { AppComponent } from '../app/app.component';
import {AuthService} from "../app/security/auth.service";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {RouterTestingModule} from "@angular/router/testing";

describe('AppComponent (with beforeEach)', () => {
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;
  let authService: AuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule, AppComponent],
      providers: [AuthService]
    });
    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
    // AuthService from the root injector
    authService = TestBed.inject(AuthService);
  });
  it('should create', () => {
    expect(component).toBeDefined();
  });
});
