import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MovimentacaoService } from './movimentacoes.service';

@Component({
  selector: 'app-movimentacoes',
  standalone: true,
  imports: [CommonModule],
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
}
