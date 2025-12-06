import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_URL } from '../../../config/api.config';

@Injectable({
  providedIn: 'root'
})
export class MovimentacaoService {

  //private apiUrl = 'http://localhost:8080/api/movimentacoes';
  private apiUrl = `${API_URL}/movimentacoes`;

  constructor(private http: HttpClient) {}

  listar(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}`);
  }

  
}
