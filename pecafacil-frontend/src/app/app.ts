import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from "./shared/navbar/navbar.component";
import { AuthService } from './auth/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,        // 👈 Necessário para usar *ngIf
    RouterOutlet,
    NavbarComponent
  ],
  templateUrl: './app.html'
})
export class AppComponent {

  constructor(
    private router: Router,
    private auth: AuthService
  ) {}

  mostrarNavbar(): boolean {
    const urlAtual = this.router.url;

    // Navbar NÃO aparece antes do login
    if (urlAtual === '/login') return false;

    // Navbar só aparece quando logado
    return this.auth.isLoggedIn();
  }
}
