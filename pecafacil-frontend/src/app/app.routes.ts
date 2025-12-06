import { Routes } from '@angular/router';

import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { AuthGuard } from './auth/auth.guard';
import { AdminGuard } from './auth/admin.guard';
import { ProdutosComponent } from './components/produtos/produtos.component';

// 1. ADICIONE O IMPORT AQUI (Verifique se o caminho da pasta está certo)
import { ConfiguracoesComponent } from './pages/configuracoes/configuracoes.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  // Rota padrão
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  // Acesso geral (usuários logados)
  { path: 'produtos', component: ProdutosComponent, canActivate: [AuthGuard] },

  // 2. ADICIONE A ROTA NOVA AQUI
  // (Sugiro adicionar o AdminGuard também, já que é pra aprovar usuários)
  {
    path: 'clientes',
    component: ConfiguracoesComponent,
    canActivate: [AuthGuard, AdminGuard]
  },

  // === ROTAS ADMIN (batendo com a navbar) ===
  {
    path: 'admin/auditlog',
    loadComponent: () =>
      import('./components/admin/auditlog/auditlog.component')
        .then(m => m.AuditLogComponent),
    canActivate: [AuthGuard, AdminGuard]
  },

  {
    path: 'admin/movimentacoes',
    loadComponent: () =>
      import('./components/admin/movimentacoes/movimentacoes.component')
        .then(m => m.MovimentacoesComponent),
    canActivate: [AuthGuard, AdminGuard]
  },

  // fallback – se a rota não existe, volta pro login
  { path: '**', redirectTo: 'login' }
];