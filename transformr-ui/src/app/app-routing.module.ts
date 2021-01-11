import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {
  CurrencyComponent,
  HomeComponent,
  TimeComponent,
  UnitComponent,
} from './components';
import { DataLoadedGuard } from './guards';

const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [DataLoadedGuard] },
  {
    path: 'currency',
    component: CurrencyComponent,
    canActivate: [DataLoadedGuard],
  },
  { path: 'time', component: TimeComponent, canActivate: [DataLoadedGuard] },
  {
    path: 'length',
    component: UnitComponent,
    canActivate: [DataLoadedGuard],
    data: { type: 'length' },
  },
  {
    path: 'area',
    component: UnitComponent,
    canActivate: [DataLoadedGuard],
    data: { type: 'area' },
  },
  {
    path: 'volume',
    component: UnitComponent,
    canActivate: [DataLoadedGuard],
    data: { type: 'volume' },
  },
  {
    path: 'mass',
    component: UnitComponent,
    canActivate: [DataLoadedGuard],
    data: { type: 'mass' },
  },
  {
    path: 'temperature',
    component: UnitComponent,
    canActivate: [DataLoadedGuard],
    data: { type: 'temperature' },
  },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', redirectTo: '/home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
