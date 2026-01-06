package com.desa.demo.service;

import com.desa.demo.entity.Cliente;
import com.desa.demo.service.dto.ClienteDto;

import java.util.List;

public interface ClienteService {


    ClienteDto crearCliente(ClienteDto clienteDto);


    ClienteDto obtenerClientePorIdentificacion(String identificacion);


    ClienteDto actualizarCliente(ClienteDto clienteDto);


    ClienteDto eliminarCliente(ClienteDto clienteDeleteRequestDto);


    List<Cliente> obtenerClientesPorEstado();

}
