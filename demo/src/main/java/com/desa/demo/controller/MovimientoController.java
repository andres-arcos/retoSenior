package com.desa.demo.controller;

import com.desa.demo.entity.Movimiento;
import com.desa.demo.service.MovimientoService;
import com.desa.demo.service.dto.MovimientoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<MovimientoDto> crearMovimiento(@Validated @RequestBody MovimientoDto movimientoDto) {
        log.info("Solicitud para crear movimiento: {}", movimientoDto);
        MovimientoDto movimientoCreado = movimientoService.crearMovimiento(movimientoDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(movimientoCreado.withMensaje("Movimiento creado exitosamente"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDto> obtenerMovimiento(@PathVariable Long id) {
        log.info("Solicitud para obtener movimiento con ID: {}", id);
        MovimientoDto movimiento = movimientoService.obtenerMovimientoPorId(id);
        return ResponseEntity.ok(movimiento.withMensaje("Movimiento obtenido exitosamente"));
    }

    @GetMapping("/cuenta/{numeroCuenta}")
    public ResponseEntity<List<Movimiento>> obtenerMovimientosPorCuenta(@PathVariable String numeroCuenta) {
        log.info("Solicitud para obtener movimientos de la cuenta: {}", numeroCuenta);
        List<Movimiento> movimientos = movimientoService.obtenerMovimientosPorCuenta(numeroCuenta);
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Movimiento>> obtenerMovimientosPorClienteYFechas(
            @PathVariable Long clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        
        log.info("Solicitud para obtener movimientos del cliente {} entre {} y {}", clienteId, fechaInicio, fechaFin);
        List<Movimiento> movimientos = movimientoService.obtenerMovimientosPorClienteYFechas(clienteId, fechaInicio, fechaFin);
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/saldo/{numeroCuenta}")
    public ResponseEntity<BigDecimal> obtenerSaldoActual(@PathVariable String numeroCuenta) {
        log.info("Solicitud para obtener saldo de la cuenta: {}", numeroCuenta);
        BigDecimal saldo = movimientoService.obtenerSaldoActual(numeroCuenta);
        return ResponseEntity.ok(saldo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable Long id) {
        log.info("Solicitud para eliminar movimiento con ID: {}", id);
        movimientoService.eliminarMovimiento(id);
        return ResponseEntity.noContent().build();
    }
}