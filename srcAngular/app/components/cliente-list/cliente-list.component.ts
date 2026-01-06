import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClienteService } from '../../services/cliente.service';
import { Cliente } from '../../models/cliente.model';

@Component({
  selector: 'app-cliente-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cliente-list.component.html',
  styleUrls: ['./cliente-list.component.css']
})
export class ClienteListComponent implements OnInit {
  clientes: Cliente[] = [];
  isLoading: boolean = true;
  error: string | null = null;

  constructor(private clienteService: ClienteService) {}

  ngOnInit(): void {
    this.loadClientes();
  }

  loadClientes(): void {
    console.log('Iniciando carga de clientes...');
    this.isLoading = true;
    this.error = null;
    
    this.clienteService.getClientes().subscribe({
      next: (data) => {
        console.log('Datos recibidos:', data);
        this.clientes = data;
        this.isLoading = false;
        console.log('Clientes cargados:', this.clientes);
      },
      error: (err) => {
        console.error('Error al cargar clientes:', err);
        this.error = 'Error al cargar la lista de clientes. Por favor, intente nuevamente.';
        this.isLoading = false;
        console.log('Estado después del error:', { isLoading: this.isLoading, error: this.error });
      }
    });
  }
}
