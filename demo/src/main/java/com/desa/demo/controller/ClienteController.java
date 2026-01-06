package com.desa.demo.controller;

import com.desa.demo.entity.Cliente;
import com.desa.demo.service.ClienteService;
import com.desa.demo.service.dto.ClienteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    @PostMapping
    public ResponseEntity<ClienteDto> crearCliente(
            @Validated @RequestBody ClienteDto clienteRequestDto) {

        ClienteDto clienteCreado = clienteService.crearCliente(clienteRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteCreado.withMensaje("Cliente creado correctamente"));
    }


    @GetMapping("/lista")
    public ResponseEntity<List<Cliente>> obtenerClientes() {
        return new ResponseEntity<>(clienteService.obtenerClientesPorEstado(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ClienteDto> actualizarCliente(@Validated @RequestBody final ClienteDto clienteRequestDto) {
        ClienteDto clienteActualizado = clienteService.actualizarCliente(clienteRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteActualizado.withMensaje("Cliente actualizado correctamente"));
    }

    @DeleteMapping
    public ResponseEntity<ClienteDto> eliminarCliente(@Validated @RequestBody final ClienteDto clienteRequestDto) {
        ClienteDto clienteEliminado = clienteService.eliminarCliente(clienteRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteEliminado.withMensaje("Cliente actualizado correctamente"));
    }
}
