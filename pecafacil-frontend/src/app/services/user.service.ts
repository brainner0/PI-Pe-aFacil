import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface User {
  id: number;
  fullName: string;
  username: string;
  email: string;
  cpf: string;
  role: string;
  active: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/users'; // Verifique se a porta é 8080 mesmo

  constructor(private http: HttpClient) {}

  // Busca só os inativos
  listarPendentes(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/pending`);
  }
  listarAtivos(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/active`);
  }

  // Aprova o usuário
  aprovarUsuario(id: number): Observable<void> {
    return this.http.patch<void>(`${this.apiUrl}/${id}/approve`, {});
  }

  // (Opcional) Recusa/Exclui o usuário
  recusarUsuario(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  
}