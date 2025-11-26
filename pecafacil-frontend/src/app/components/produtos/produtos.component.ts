import { Component, OnInit } from '@angular/core';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Produto, ProdutoService } from '../../services/produto.service';
import { AuthService } from '../../auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-produtos',
  standalone: true,
  imports: [CommonModule, FormsModule, CurrencyPipe],
  templateUrl: './produtos.html',
  styleUrls: ['./produtos.css']
})
export class ProdutosComponent implements OnInit {
  
  username: string | null = null;  // ← Agora o template acessa isso!

  produtos: Produto[] = [];
  novoProduto: Produto = { nome: '', descricao: '', preco: undefined as any, quantidade: undefined as any, marca: '' };

  filtro: string = '';

  constructor(
    private produtoService: ProdutoService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.username = localStorage.getItem('username'); // ← Pegando do localStorage
    this.carregarProdutos();
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  carregarProdutos(): void {
    this.produtoService.listarProdutos().subscribe({
      next: (data) => (this.produtos = data),
      error: (err) => console.error('Erro ao listar produtos', err)
    });
  }

  get produtosFiltrados() {
    const termo = this.filtro
      .normalize('NFD')
      .replace(/\p{Diacritic}/gu, '')
      .toLowerCase()
      .trim();

    if (!termo) return this.produtos;

    return this.produtos.filter(p =>
      (p.id ?? '').toString().includes(termo) ||
      (p.nome ?? '').normalize('NFD').replace(/\p{Diacritic}/gu, '').toLowerCase().includes(termo) ||
      (p.descricao ?? '').normalize('NFD').replace(/\p{Diacritic}/gu, '').toLowerCase().includes(termo) ||
      (p.marca ?? '').normalize('NFD').replace(/\p{Diacritic}/gu, '').toLowerCase().includes(termo)
    );
  }

  salvarProduto(): void {
    this.produtoService.salvarProduto(this.novoProduto).subscribe({
      next: () => {
        this.novoProduto = { nome: '', descricao: '', preco: undefined as any, quantidade: undefined as any, marca: '' };
        this.carregarProdutos();
      },
      error: (err) => console.error('Erro ao salvar produto', err)
    });
  }

  entrada(id: number): void {
    const quantidade = prompt('Quantidade de entrada:');
    if (quantidade) {
      this.produtoService.registrarEntrada(id, Number(quantidade)).subscribe({
        next: () => this.carregarProdutos(),
        error: (err) => console.error('Erro ao registrar entrada', err)
      });
    }
  }

  saida(id: number): void {
    const quantidade = prompt('Quantidade de saída:');
    if (quantidade) {
      this.produtoService.registrarSaida(id, Number(quantidade)).subscribe({
        next: () => this.carregarProdutos(),
        error: (err) => console.error('Erro ao registrar saída', err)
      });
    }
  }

  excluir(id: number): void {
    if (confirm('Tem certeza que deseja excluir este produto?')) {
      this.produtoService.deletarProduto(id).subscribe({
        next: () => this.carregarProdutos(),
        error: (err) => console.error('Erro ao excluir produto', err)
      });
    }
  }
}
