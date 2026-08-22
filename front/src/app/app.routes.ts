import { Routes } from '@angular/router';


export const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',

  },
  {
    path: 'login',
    loadComponent: () => import('./pages/login/login').then(m => m.login),
  },
  {
    path: 'citas',
    loadComponent: () => import('./pages/citas/citas').then(m => m.Citas),
    data: { title: 'Gestión de Citas' }
  },
  {
    path: 'mascotas',
    loadComponent: () => import('./pages/mascotas/mascotas').then(m => m.Mascotas),
    data: { title: 'Lista de mascotas' }
  },
  {
    path: 'duenos',
    loadComponent: () => import('./pages/duenos/duenos').then(m => m.Duenos),
    data: { title: 'Lista de dueños' }
  },
  {
    path: 'veterinarios',
    loadComponent: () => import('./pages/veterinarios/veterinarios').then(m => m.Veterinarios),
    data: { title: 'Equipo Veterinario' }
  },
  {
    path: '**',
    redirectTo: 'login'
  }
];
