import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Produto {
  id?: number;
  nome: string;
  descricao: string;
  preco: number;
  quantidade: number;
  marca: string;
   local?: string;
  dataUltimaEntrada?: string;
  dataUltimaSaida?: string;
  estoqueMinimo?: number;
   expandido?: boolean;
}


@Injectable({
  providedIn: 'root'
})
export class ProdutoService {
  private apiUrl = 'http://localhost:8080/api/produtos'; // 👈 use o mesmo prefixo do seu backend

  constructor(private http: HttpClient) {}

  listarProdutos(): Observable<Produto[]> {
    return this.http.get<Produto[]>(this.apiUrl);
  }

  salvarProduto(produto: Produto): Observable<Produto> {
    return this.http.post<Produto>(this.apiUrl, produto);
  }

  registrarEntrada(id: number, quantidade: number): Observable<Produto> {
    return this.http.patch<Produto>(`${this.apiUrl}/${id}/entrada/${quantidade}`, {});
  }

  registrarSaida(id: number, quantidade: number): Observable<Produto> {
    return this.http.patch<Produto>(`${this.apiUrl}/${id}/saida/${quantidade}`, {});
  }

  deletarProduto(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
