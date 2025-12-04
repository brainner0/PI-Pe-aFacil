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

  username: string | null = null;
  isAdmin = false;

  produtos: Produto[] = [];
  novoProduto: Produto = {
    nome: '',
    descricao: '',
    preco: undefined as any,
    quantidade: undefined as any,
    marca: '',
    estoqueMinimo: undefined as any,
    local: ''
  };

  // filtros
  filtro: string = '';
  statusFiltro: 'todos' | 'disponiveis' | 'baixo' | 'esgotados' = 'todos';
  localFiltro: string = 'todos';

  // modal "Novo Produto"
  mostrarModalNovo = false;

  editandoId: number | null = null;

  // modal de movimentação (entrada / saída)
  mostrarModalMov = false;
  tipoMov: 'entrada' | 'saida' | null = null;
  produtoSelecionado: Produto | null = null;
  quantidadeMov: number | null = null;

  constructor(
    private produtoService: ProdutoService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.username = localStorage.getItem('username');
    this.isAdmin = localStorage.getItem('role') === 'ROLE_ADMIN';
    this.carregarProdutos();
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  carregarProdutos(): void {
    this.produtoService.listarProdutos().subscribe({
      next: (data) => {
        this.produtos = data.sort((a, b) =>
          a.nome.localeCompare(b.nome, 'pt-BR', { sensitivity: 'base' })
        );
      },
      error: (err) => console.error('Erro ao listar produtos', err)
    });
  }

  // ======== ESTATÍSTICAS ========

  get totalItens(): number {
    return this.produtos.length;
  }

  get disponiveis(): number {
    return this.produtos.filter(p => !this.isEsgotado(p)).length;
  }

  get baixoEstoqueCount(): number {
    return this.produtos.filter(p => this.isBaixoEstoque(p)).length;
  }

  get esgotados(): number {
    return this.produtos.filter(p => this.isEsgotado(p)).length;
  }

  get locaisDisponiveis(): string[] {
    return Array.from(
      new Set(
        this.produtos
          .map(p => p.local)
          .filter((v): v is string => !!v)
      )
    ).sort();
  }

  // ======== REGRAS DE ESTOQUE ========

  isEsgotado(p: Produto): boolean {
    const qtd = p.quantidade ?? 0;
    return qtd === 0;
  }

  isBaixoEstoque(p: Produto): boolean {
    const qtd = p.quantidade ?? 0;
    const min = p.estoqueMinimo ?? 0;
    return qtd > 0 && min > 0 && qtd <= min;
  }

  // ======== FILTRO ========

  get produtosFiltrados(): Produto[] {
    const termo = this.filtro
      .normalize('NFD')
      .replace(/\p{Diacritic}/gu, '')
      .toLowerCase()
      .trim();

    return this.produtos.filter(p => {
      const termoOk =
        !termo ||
        (p.id ?? '').toString().includes(termo) ||
        (p.nome ?? '').normalize('NFD').replace(/\p{Diacritic}/gu, '').toLowerCase().includes(termo) ||
        (p.descricao ?? '').normalize('NFD').replace(/\p{Diacritic}/gu, '').toLowerCase().includes(termo) ||
        (p.marca ?? '').normalize('NFD').replace(/\p{Diacritic}/gu, '').toLowerCase().includes(termo) ||
        (p.local ?? '').normalize('NFD').replace(/\p{Diacritic}/gu, '').toLowerCase().includes(termo);

      let statusOk = true;
      if (this.statusFiltro === 'disponiveis') {
        statusOk = !this.isEsgotado(p);
      } else if (this.statusFiltro === 'baixo') {
        statusOk = this.isBaixoEstoque(p);
      } else if (this.statusFiltro === 'esgotados') {
        statusOk = this.isEsgotado(p);
      }

      const localOk =
        this.localFiltro === 'todos' ||
        (p.local ?? '') === this.localFiltro;

      return termoOk && statusOk && localOk;
    });
  }

  // ======== MODAL NOVO PRODUTO ========

  abrirModalNovo(): void {
    this.mostrarModalNovo = true;
    
  }

  fecharModalNovo(): void {
    this.mostrarModalNovo = false;
    this.editandoId = null; 

  this.novoProduto = {
    nome: '',
    descricao: '',
    preco: undefined as any,
    quantidade: undefined as any,
    marca: '',
    estoqueMinimo: undefined as any,
    local: ''
  };
  }

  salvarProduto(): void {
  // Se tiver um ID de edição, chama o ATUALIZAR
  if (this.editandoId) {
    this.produtoService.atualizarProduto(this.editandoId, this.novoProduto).subscribe({
      next: () => {
        this.fecharModalNovo();
        this.carregarProdutos();
      },
      error: (err) => console.error('Erro ao atualizar produto', err)
    });
  } 
  // Se não, chama o SALVAR (CRIAR NOVO)
  else {
    this.produtoService.salvarProduto(this.novoProduto).subscribe({
      next: () => {
        this.fecharModalNovo();
        this.carregarProdutos();
      },
      error: (err) => console.error('Erro ao salvar produto', err)
    });
  }
}
  // ======== MODAL DE EDIÇAO ========

  abrirModalEditar(produto: Produto): void {
  this.editandoId = produto.id!; 
  this.novoProduto = { ...produto }; 
  this.mostrarModalNovo = true;
}

  // ======== MODAL DE MOVIMENTAÇÃO ========

  abrirMov(p: Produto, tipo: 'entrada' | 'saida'): void {
    this.produtoSelecionado = p;
    this.tipoMov = tipo;
    this.quantidadeMov = null;
    this.mostrarModalMov = true;
  }

  fecharMov(): void {
    this.mostrarModalMov = false;
    this.produtoSelecionado = null;
    this.tipoMov = null;
    this.quantidadeMov = null;
  }

  confirmarMov(): void {
    if (!this.produtoSelecionado || !this.tipoMov || !this.quantidadeMov || this.quantidadeMov <= 0) {
      return;
    }

    const id = this.produtoSelecionado.id!;
    const qtd = this.quantidadeMov;

    const obs =
      this.tipoMov === 'entrada'
        ? this.produtoService.registrarEntrada(id, qtd)
        : this.produtoService.registrarSaida(id, qtd);

    obs.subscribe({
      next: () => {
        this.fecharMov();
        this.carregarProdutos();
      },
      error: (err) => console.error('Erro ao registrar movimentação', err)
    });
  }

  // ======== EXCLUIR (apenas admin no template) ========

  excluir(id: number): void {
    if (confirm('Tem certeza que deseja excluir este produto?')) {
      this.produtoService.deletarProduto(id).subscribe({
        next: () => this.carregarProdutos(),
        error: (err) => console.error('Erro ao excluir produto', err)
      });
    }
  }
}
