import { Routes } from '@angular/router';
import { PrincipalComponent } from './components/layout/principal/principal.component';
import { LoginComponent } from './components/layout/login/login.component';
import { CarroslistComponent } from './components/carro/carroslist/carroslist.component';
import { CarrosdetailsComponent } from './components/carro/carrosdetails/carrosdetails.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'admin',
    component: PrincipalComponent,
    children: [
      {
        path: 'carros',
        component: CarroslistComponent,
      },
      {
        path: 'carros/new',
        component: CarrosdetailsComponent,
      },
      {
        path: 'carros/edit/:id',
        component: CarrosdetailsComponent,
      },
    ],
  },
];
