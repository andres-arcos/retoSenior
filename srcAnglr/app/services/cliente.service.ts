import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cliente } from '../models/cliente.model';

export interface NewCliente {
  nombre: string;
  genero: string;
  edad: number;
  identificacion: string;
  direccion: string;
  telefono: string;
  estado: boolean;
  clienteId: string;
  contrasena: string;
}

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private apiUrl = '/api/clientes';

  constructor(private http: HttpClient) { }

  getClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(`${this.apiUrl}/lista`);
  }

  createCliente(cliente: NewCliente): Observable<Cliente> {
    return this.http.post<Cliente>(this.apiUrl, cliente);
  }
}
