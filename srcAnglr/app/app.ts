import { Component, signal } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ClienteListComponent } from './components/cliente-list/cliente-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule, ClienteListComponent],
  template: `
    <h1>{{ title() }}</h1>
    <router-outlet></router-outlet>
  `,
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('Gestión de Clientes');
}
