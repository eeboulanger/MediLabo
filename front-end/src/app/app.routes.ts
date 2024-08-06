import {PatientListComponent} from "./components/patient-list/patient-list.component";
import {PatientEditComponent} from "./components/patient-edit/patient-edit.component";
import {PatientAddComponent} from "./components/patient-add/patient-add.component";
import {HomeComponent} from "./components/home/home.component";
import {LoginComponent} from "./components/login/login.component";
import {AuthGuard} from "./security/auth.guard";

export const routes = [
  {path: '', component: HomeComponent},
  {path: 'patients', component: PatientListComponent, canActivate: [AuthGuard]},
  {path: 'patient-edit/:id', component: PatientEditComponent, canActivate: [AuthGuard]},
  {path: 'patient-add', component: PatientAddComponent, canActivate: [AuthGuard]},
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
];
