import {PatientListComponent} from "./components/patient-list/patient-list.component";
import {PatientEditComponent} from "./components/patient-edit/patient-edit.component";
import {PatientAddComponent} from "./components/patient-add/patient-add.component";

export const routes = [
  {path: '', component: PatientListComponent},
  {path: 'patients', component: PatientListComponent},
  {path: 'patient-edit/:id', component: PatientEditComponent},
  {path: 'patient-add', component: PatientAddComponent}
];
