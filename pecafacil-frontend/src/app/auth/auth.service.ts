import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { API_URL } from '../config/api.config';

export interface AuthResponse {
  token: string;
  username: string;
  role: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // ROTA BASE CORRETA
  //private apiUrl = 'http://localhost:8080/api/auth';
  private apiUrl = `${API_URL}/auth`;

  constructor(private http: HttpClient) {}

  // LOGIN
  login(username: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, {
      username,
      password
    }).pipe(
      tap(res => this.saveSession(res))
    );
  }

  // REGISTRO SIMPLES
  register(username: string, password: string, role: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, {
      username,
      password,
      role
    }).pipe(
      tap(res => this.saveSession(res))
    );
  }

  // REGISTRO COMPLETO  ✔ CORRIGIDO
  registerComplete(data: any) {
    return this.http.post(`${this.apiUrl}/register`, data);
  }

  // SALVAR TOKEN
  private saveSession(auth: AuthResponse): void {
    localStorage.setItem('token', auth.token);
    localStorage.setItem('username', auth.username);
    localStorage.setItem('role', auth.role);
  }

  // LOGOUT
  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    localStorage.removeItem('role');
  }

  // CONSULTAR CPF  ✔ CORRIGIDO
  verificarCpf(cpf: string) {
    return this.http.get<boolean>(`${this.apiUrl}/verificar-cpf/${cpf}`);
  }

  // CONSULTAR USERNAME ✔ CORRIGIDO
  verificarUsername(username: string) {
    return this.http.get<boolean>(`${this.apiUrl}/verificar-username/${username}`);
  }

  // GETTERS
  getToken(): string | null { return localStorage.getItem('token'); }
  getRole(): string | null { return localStorage.getItem('role'); }
  getUsername(): string | null { return localStorage.getItem('username'); }

  isLoggedIn(): boolean { return !!this.getToken(); }
  isAdmin(): boolean { return this.getRole() === 'ROLE_ADMIN'; }
}
