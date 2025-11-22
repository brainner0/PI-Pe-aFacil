import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../auth.service';
import { ViaCepService } from '../viacep.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  // Campos do formulário
  fullName = '';
  cpf = '';
  phone = '';
  email = '';
  cep = '';
  street = '';
  number = '';
  complement = '';
  district = '';
  city = '';
  state = '';
  username = '';
  password = '';
  role = 'ROLE_USER';

  // Estados de validação
  cpfValido: boolean | null = null;
  cpfExistente: boolean | null = null;

  usernameExistente: boolean | null = null;

  loading = false;
  errorMessage = '';

  constructor(
    private authService: AuthService,
    private viaCepService: ViaCepService,
    private router: Router
  ) {}

  // ===========================================================
  // 📌 MÁSCARAS
  // ===========================================================

  formatarCPF(): void {
    if (!this.cpf) return;
    let v = this.cpf.replace(/\D/g, '');
    v = v.replace(/(\d{3})(\d)/, '$1.$2');
    v = v.replace(/(\d{3})(\d)/, '$1.$2');
    v = v.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
    this.cpf = v;
  }

  formatarPhone(): void {
    if (!this.phone) return;
    let v = this.phone.replace(/\D/g, '');
    v = v.replace(/(\d{2})(\d)/, '($1) $2');
    v = v.replace(/(\d{5})(\d)/, '$1-$2');
    this.phone = v;
  }

  formatarCEP(): void {
    if (!this.cep) return;
    let v = this.cep.replace(/\D/g, '');
    v = v.replace(/(\d{5})(\d)/, '$1-$2');
    this.cep = v;
  }

  // ===========================================================
  // 🧮 CPF — Validação oficial
  // ===========================================================

  validarCPF(): void {
    const cpfLimpo = this.cpf.replace(/\D/g, '');
    if (cpfLimpo.length !== 11) {
      this.cpfValido = false;
      return;
    }

    if (/^(\d)\1+$/.test(cpfLimpo)) {
      this.cpfValido = false;
      return;
    }

    let soma = 0;
    for (let i = 0; i < 9; i++) soma += Number(cpfLimpo[i]) * (10 - i);
    let resto = (soma * 10) % 11;
    if (resto === 10) resto = 0;
    if (resto !== Number(cpfLimpo[9])) {
      this.cpfValido = false;
      return;
    }

    soma = 0;
    for (let i = 0; i < 10; i++) soma += Number(cpfLimpo[i]) * (11 - i);
    resto = (soma * 10) % 11;
    if (resto === 10) resto = 0;

    this.cpfValido = resto === Number(cpfLimpo[10]);

    if (this.cpfValido) this.verificarCPFExistente();
  }

  // ===========================================================
  // 🔍 CPF já cadastrado? 
  // ===========================================================

  verificarCPFExistente(): void {
    const cpfLimpo = this.cpf.replace(/\D/g, '');

    if (!cpfLimpo || cpfLimpo.length !== 11) return;

    this.authService.verificarCpf(cpfLimpo).subscribe({
      next: (res) => this.cpfExistente = res,
      error: () => this.cpfExistente = false
    });
  }

  // ===========================================================
  // 🔍 USERNAME — verificar disponibilidade
  // ===========================================================

  verificarUsername(): void {
    if (!this.username || this.username.length < 3) {
      this.usernameExistente = null;
      return;
    }

    this.authService.verificarUsername(this.username).subscribe({
      next: (res) => this.usernameExistente = res,
      error: () => this.usernameExistente = false
    });
  }

  // ===========================================================
  // 🏠 VIA CEP
  // ===========================================================

  buscarCEP(): void {
    const limpo = this.cep.replace(/\D/g, '');
    if (limpo.length !== 8) return;

    this.viaCepService.buscarCEP(this.cep).subscribe({
      next: dados => {
        if (dados.erro) return;

        this.street = dados.logradouro || '';
        this.district = dados.bairro || '';
        this.city = dados.localidade || '';
        this.state = dados.uf || '';
      }
    });
  }

  // ===========================================================
  // 🔐 ENVIAR CADASTRO
  // ===========================================================

  onSubmit(): void {
    if (this.cpfValido === false || this.cpfExistente === true) {
      this.errorMessage = 'Corrija o CPF antes de continuar.';
      return;
    }

    if (this.usernameExistente === true) {
      this.errorMessage = 'Esse nome de usuário já está em uso.';
      return;
    }

    this.loading = true;
    this.errorMessage = '';

    const payload = {
      fullName: this.fullName,
      cpf: this.cpf,
      phone: this.phone,
      email: this.email,
      cep: this.cep,
      street: this.street,
      number: this.number,
      complement: this.complement,
      district: this.district,
      city: this.city,
      state: this.state,
      username: this.username,
      password: this.password,
      role: this.role
    };

    this.authService.registerComplete(payload).subscribe({
      next: () => {
        this.loading = false;
        this.router.navigate(['/login']);
      },
      error: () => {
        this.loading = false;
        this.errorMessage = 'Erro ao cadastrar.';
      }
    });
  }
}
