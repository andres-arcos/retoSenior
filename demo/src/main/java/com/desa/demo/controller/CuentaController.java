package com.desa.demo.controller;

import com.desa.demo.entity.Cuenta;
import com.desa.demo.service.CuentaService;
import com.desa.demo.service.dto.CuentaDto;
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
@RequestMapping("/api/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    
    @PostMapping
    public ResponseEntity<CuentaDto> crearCuenta(@Validated @RequestBody CuentaDto cuentaDto) {
        log.info("Solicitud para crear cuenta: {}", cuentaDto);
        CuentaDto cuentaCreada = cuentaService.crearCuenta(cuentaDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cuentaCreada.withMensaje("Cuenta creada exitosamente"));
    }

    
    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<CuentaDto> obtenerCuenta(@PathVariable String numeroCuenta) {
        log.info("Solicitud para obtener cuenta con número: {}", numeroCuenta);
        CuentaDto cuenta = cuentaService.obtenerCuentaPorNumero(numeroCuenta);
        return ResponseEntity.ok(cuenta.withMensaje("Cuenta obtenida exitosamente"));
    }

   
    @PutMapping
    public ResponseEntity<CuentaDto> actualizarCuenta(@Validated @RequestBody CuentaDto cuentaDto) {
        log.info("Solicitud para actualizar cuenta con ID: {}", cuentaDto.id());
        CuentaDto cuentaActualizada = cuentaService.actualizarCuenta(cuentaDto);
        return ResponseEntity.ok(cuentaActualizada.withMensaje("Cuenta actualizada exitosamente"));
    }

   
    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable String numeroCuenta) {
        log.info("Solicitud para eliminar cuenta con número: {}", numeroCuenta);
        cuentaService.eliminarCuenta(numeroCuenta);
        return ResponseEntity.noContent().build();
    }

   
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Cuenta>> obtenerCuentasPorCliente(@PathVariable Long clienteId) {
        log.info("Solicitud para obtener cuentas del cliente ID: {}", clienteId);
        List<Cuenta> cuentas = cuentaService.obtenerCuentasPorCliente(clienteId);
        return ResponseEntity.ok(cuentas);
    }
}
