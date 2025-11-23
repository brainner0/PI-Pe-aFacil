import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  canActivate(): boolean {
    // não logado → login
    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['/login']);
      return false;
    }

    // logado mas não é admin → produtos
    if (!this.authService.isAdmin()) {
      this.router.navigate(['/produtos']);
      return false;
    }

    // admin → pode entrar
    return true;
  }
}
