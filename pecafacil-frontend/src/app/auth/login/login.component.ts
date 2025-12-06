import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';   // 👈 necessário para routerLink
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterLink   // 👈 IMPORTANTE para <a routerLink="...">
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username = '';
  password = '';
  loading = false;
  errorMessage = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onSubmit(): void {
    if (!this.username || !this.password) {
      this.errorMessage = 'Preencha usuário e senha.';
      return;
    }

    this.errorMessage = '';
    this.loading = true;

    this.authService.login(this.username, this.password).subscribe({
      next: () => {
        this.loading = false;
        this.router.navigate(['/produtos']);
      },
      error: (err) => {
        this.loading = false;
        console.log('Erro completo:', err);

        // Pega a mensagem de texto do erro (se houver)
        const msg = err.error?.message || err.error || '';
        const textoErro = msg.toString().toLowerCase();

        // --- LÓGICA NOVA ---

        // 1. Se a mensagem diz explicitamente que está pendente/aprovação
        if (textoErro.includes('pendente') || textoErro.includes('aprovação')) {
           this.errorMessage = '⏳ Seu cadastro está em análise. Aguarde a aprovação do administrador.';
        } 
        // 2. Se NÃO for pendente, mas deu erro 403 ou 401, então é Senha/Usuário errado
        else if (err.status === 401 || err.status === 403) {
           this.errorMessage = 'Usuário ou senha inválidos.';
        } 
        // 3. Qualquer outro erro (Servidor desligado, erro 500, etc)
        else {
           this.errorMessage = 'Erro ao entrar. Verifique seus dados.';
        }
      }
    });
  }
}
