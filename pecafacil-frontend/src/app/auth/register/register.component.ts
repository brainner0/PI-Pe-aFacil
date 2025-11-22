import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';   // 👈 IMPORTANTE
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterLink   // 👈 SEM ISSO O routerLink NÃO FUNCIONA!
  ],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  username = '';
  password = '';
  role = 'USER';
  loading = false;
  errorMessage = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onSubmit(): void {
    this.errorMessage = '';
    this.loading = true;

    this.authService.register(this.username, this.password, this.role).subscribe({
      next: () => {
        this.loading = false;
        this.router.navigate(['/produtos']);
      },
      error: (err) => {
        this.loading = false;
        this.errorMessage = 'Não foi possível cadastrar o usuário.';
        console.error('Erro no registro', err);
      }
    });
  }
}
