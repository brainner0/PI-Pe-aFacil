import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ProdutosComponent } from './components/produtos/produtos.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ProdutosComponent],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App {}
