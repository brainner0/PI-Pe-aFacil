import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MovimentacaoService } from './movimentacoes.service';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-movimentacoes',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './movimentacoes.component.html',
  styleUrls: ['./movimentacoes.component.css']
})
export class MovimentacoesComponent implements OnInit {

  lista: any[] = [];
  loading = true;

  constructor(private movService: MovimentacaoService) {}

  ngOnInit(): void {
    this.movService.listar().subscribe({
      next: (dados) => {
        this.lista = dados;
        this.loading = false;
      },
      error: () => this.loading = false
    });
  }
  
  searchTerm = '';

  get filteredLista() {
    if (!this.searchTerm) return this.lista;
    
    const term = this.searchTerm.toLowerCase();
    return this.lista.filter(m =>
      m.produtoNome?.toLowerCase().includes(term) ||
      m.tipo?.toLowerCase().includes(term) ||
      m.usuario?.toLowerCase().includes(term)
    );
  }

}
