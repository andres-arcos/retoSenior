import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ClienteService, NewCliente } from '../../services/cliente.service';
import { Cliente } from '../../models/cliente.model';

@Component({
  selector: 'app-cliente-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './cliente-list.component.html',
  styleUrls: ['./cliente-list.component.css']
})
export class ClienteListComponent implements OnInit {
  clientes: Cliente[] = [];
  isLoading: boolean = true;
  error: string | null = null;
  showCreateForm: boolean = false;
  isSubmitting: boolean = false;
  formError: string | null = null;
  
  newCliente: NewCliente = {
    nombre: '',
    genero: 'M',
    edad: 0,
    identificacion: '',
    direccion: '',
    telefono: '',
    estado: true,
    clienteId: '',
    contrasena: ''
  };

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
        this.clientes = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error al cargar clientes:', err);
        this.error = 'Error al cargar la lista de clientes. Por favor, intente nuevamente.';
        this.isLoading = false;
      }
    });
  }

  toggleCreateForm(): void {
    this.showCreateForm = !this.showCreateForm;
    if (!this.showCreateForm) {
      this.resetForm();
    }
  }

  onSubmit(): void {
    this.isSubmitting = true;
    this.formError = null;

    this.clienteService.createCliente(this.newCliente).subscribe({
      next: (cliente) => {
        this.clientes.push(cliente);
        this.showCreateForm = false;
        this.resetForm();
        this.isSubmitting = false;
      },
      error: (err) => {
        console.error('Error al crear cliente:', err);
        this.formError = 'Error al crear el cliente. Por favor, intente nuevamente.';
        this.isSubmitting = false;
      }
    });
  }

  private resetForm(): void {
    this.newCliente = {
      nombre: '',
      genero: 'M',
      edad: 0,
      identificacion: '',
      direccion: '',
      telefono: '',
      estado: true,
      clienteId: '',
      contrasena: ''
    };
    this.formError = null;
  }
}
