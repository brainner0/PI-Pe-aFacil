import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  isMenuOpen = false;

  constructor(private router: Router) {}

  get isLoggedIn() {
    return !!localStorage.getItem('token');
  }

  get username() {
    return localStorage.getItem('username');
  }

  get role() {
    return localStorage.getItem('role');
  }

  get isAdmin() {
    return this.role === 'ROLE_ADMIN';
  }

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }

  goTo(path: string) {
    this.router.navigate([`/${path}`]);
    this.isMenuOpen = false;
  }

  logout() {
    localStorage.clear();
    this.isMenuOpen = false;
    this.router.navigate(['/login']);
  }
}
