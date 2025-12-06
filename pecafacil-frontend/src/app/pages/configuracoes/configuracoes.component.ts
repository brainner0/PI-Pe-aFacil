import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { User, UserService } from '../../services/user.service';

@Component({
  selector: 'app-configuracoes',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './configuracoes.component.html',
  styleUrls: ['./configuracoes.component.css']
})
export class ConfiguracoesComponent implements OnInit {

  pendentes: User[] = [];
  ativos: User[] = []; // Nova lista
  
  // Controle do Modal
  usuarioSelecionado: User | null = null;
  tipoAcao: 'aprovar' | 'recusar' | 'excluir' | null = null; // Adicionado 'excluir'

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.carregarDados();
  }

  carregarDados(): void {
    // Carrega Pendentes
    this.userService.listarPendentes().subscribe({
      next: (data) => this.pendentes = data,
      error: (err) => console.error('Erro pendentes:', err)
    });

    // Carrega Ativos
    this.userService.listarAtivos().subscribe({
      next: (data) => this.ativos = data,
      error: (err) => console.error('Erro ativos:', err)
    });
  }

  // Ações da Tabela
  iniciarAprovacao(user: User): void {
    this.usuarioSelecionado = user;
    this.tipoAcao = 'aprovar';
  }

  iniciarRecusa(user: User): void {
    this.usuarioSelecionado = user;
    this.tipoAcao = 'recusar';
  }

  // Nova ação para usuários ativos
  iniciarExclusao(user: User): void {
    this.usuarioSelecionado = user;
    this.tipoAcao = 'excluir';
  }

  fecharModal(): void {
    this.usuarioSelecionado = null;
    this.tipoAcao = null;
  }

  confirmarAcao(): void {
    if (!this.usuarioSelecionado || !this.tipoAcao) return;

    const id = this.usuarioSelecionado.id;

    if (this.tipoAcao === 'aprovar') {
      this.userService.aprovarUsuario(id).subscribe({
        next: () => this.finalizarAcao(),
        error: () => alert('Erro ao aprovar.')
      });
    } 
    // Tanto Recusar (pendente) quanto Excluir (ativo) usam o mesmo delete do backend
    else if (this.tipoAcao === 'recusar' || this.tipoAcao === 'excluir') {
      this.userService.recusarUsuario(id).subscribe({
        next: () => this.finalizarAcao(),
        error: () => alert('Erro ao remover usuário.')
      });
    }
  }

  finalizarAcao(): void {
    this.fecharModal();
    this.carregarDados(); // Recarrega todas as listas
  }
}