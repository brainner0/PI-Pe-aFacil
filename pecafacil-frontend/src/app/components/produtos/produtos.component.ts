import { Component, OnInit } from '@angular/core';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Produto, ProdutoService } from '../../services/produto.service';

@Component({
  selector: 'app-produtos',
  standalone: true,
  imports: [CommonModule, FormsModule, CurrencyPipe],
  templateUrl: './produtos.html',
  styleUrls: ['./produtos.css']
})
export class ProdutosComponent implements OnInit {
  produtos: Produto[] = [];
  novoProduto: Produto = { nome: '', descricao: '', preco: 0, quantidade: 0, fornecedor: '' };

  constructor(private produtoService: ProdutoService) {}

  ngOnInit(): void {
    this.carregarProdutos();
  }

  carregarProdutos(): void {
    this.produtoService.listarProdutos().subscribe({
      next: (data) => (this.produtos = data),
      error: (err) => console.error('Erro ao listar produtos', err)
    });
  }

  salvarProduto(): void {
    this.produtoService.salvarProduto(this.novoProduto).subscribe({
      next: () => {
        this.novoProduto = { nome: '', descricao: '', preco: 0, quantidade: 0, fornecedor: '' };
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
