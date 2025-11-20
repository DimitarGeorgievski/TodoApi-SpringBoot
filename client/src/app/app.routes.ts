import { Routes } from '@angular/router';
import { NotFound } from './core/components/not-found/not-found';
import { AuthLayout } from './layout/auth-layout/auth-layout';
import { Login } from './feature/login/login';
import { Todos } from './feature/todos/todos';
import { authGuard } from './core/guards/auth-guard';

export const routes: Routes = [
  {
    path: 'login',
    component: AuthLayout,
    children: [{ path: '', component: Login }],
  },
  {
    path: 'todos',
    canActivate: [authGuard],
    component: Todos,
  },
  {
    path: 'todos/:id',
    loadComponent: () => import('./feature/todo/todo').then((m) => m.Todo),
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'todos',
  },
  {
    path: '**',
    component: NotFound,
  },
];
