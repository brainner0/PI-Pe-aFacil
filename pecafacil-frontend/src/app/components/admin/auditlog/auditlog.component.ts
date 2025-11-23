import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms'; // ← Adicione esta linha
import { AuditLogService } from './auditlog.service';

@Component({
  selector: 'app-auditlog',
  standalone: true,
  imports: [CommonModule, HttpClientModule, FormsModule], // ← Adicione FormsModule aqui
  templateUrl: './auditlog.component.html',
  styleUrls: ['./auditlog.component.css']
})
export class AuditLogComponent implements OnInit {

  logs: any[] = [];
  loading = true;
  searchTerm: string = '';

  constructor(private auditService: AuditLogService) {}

  ngOnInit(): void {
    this.loadAuditLogs();
  }

  loadAuditLogs(): void {
    this.auditService.listar().subscribe({
      next: dados => {
        this.logs = dados;
        this.loading = false;
      },
      error: () => this.loading = false
    });
  }

  get filteredLogs() {
    if (!this.searchTerm) return this.logs;
    
    const term = this.searchTerm.toLowerCase();
    return this.logs.filter(log => 
      log.usuario?.toLowerCase().includes(term) ||
      log.acao?.toLowerCase().includes(term) ||
      log.detalhes?.toLowerCase().includes(term)
    );
  }
}