package com.desa.demo.service.impl;

import com.desa.demo.entity.Cuenta;
import com.desa.demo.entity.Movimiento;
import com.desa.demo.exception.CuentaException;
import com.desa.demo.exception.MovimientoException;
import com.desa.demo.mapper.MovimientoMapper;
import com.desa.demo.repository.CuentaRepository;
import com.desa.demo.repository.MovimientoRepository;
import com.desa.demo.service.MovimientoService;
import com.desa.demo.service.dto.MovimientoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;
    private final MovimientoMapper movimientoMapper;

    @Override
    @Transactional
    public MovimientoDto crearMovimiento(MovimientoDto movimientoDto) {
        log.info("Creando movimiento para la cuenta: {}", movimientoDto.numeroCuenta());
        
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(movimientoDto.numeroCuenta())
                .orElseThrow(() -> new CuentaException("No se encontró la cuenta: " + movimientoDto.numeroCuenta()));
        
        if ("RETIRO".equalsIgnoreCase(movimientoDto.tipo())) {
            BigDecimal saldoActual = obtenerSaldoActual(movimientoDto.numeroCuenta());
            if (saldoActual.compareTo(movimientoDto.valor()) < 0) {
                throw new MovimientoException("Saldo no disponible");
            }
        }
        
        BigDecimal nuevoSaldo = calcularNuevoSaldo(movimientoDto, cuenta);
        
        Movimiento movimiento = movimientoMapper.toEntity(movimientoDto);
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setCuenta(cuenta);
        movimiento.setSaldo(nuevoSaldo);
        
        Movimiento movimientoCreado = movimientoRepository.save(movimiento);
        log.info("Movimiento creado exitosamente con ID: {}", movimientoCreado.getId());
        
        return movimientoMapper.toDto(movimientoCreado);
    }

    @Override
    public MovimientoDto obtenerMovimientoPorId(Long id) {
        log.info("Obteniendo movimiento con ID: {}", id);
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new MovimientoException("No se encontró movimiento con ID: " + id));
        return movimientoMapper.toDto(movimiento);
    }

    @Override
    public List<Movimiento> obtenerMovimientosPorCuenta(String numeroCuenta) {
        log.info("Obteniendo movimientos para la cuenta: {}", numeroCuenta);
        return movimientoRepository.findByNumeroCuenta(numeroCuenta);
    }

    @Override
    public List<Movimiento> obtenerMovimientosPorClienteYFechas(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        log.info("Obteniendo movimientos para el cliente ID: {} entre {} y {}", clienteId, fechaInicio, fechaFin);
        return movimientoRepository.findByClienteIdAndFechaBetween(
                clienteId, 
                fechaInicio.atStartOfDay(), 
                fechaFin.plusDays(1).atStartOfDay()
        );
    }

    @Override
    public BigDecimal obtenerSaldoActual(String numeroCuenta) {
        log.info("Obteniendo saldo actual para la cuenta: {}", numeroCuenta);
        return movimientoRepository.findFirstByCuentaNumeroCuentaOrderByFechaDesc(numeroCuenta)
                .map(Movimiento::getSaldo)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    @Transactional
    public void eliminarMovimiento(Long id) {
        log.info("Eliminando movimiento con ID: {}", id);
        if (!movimientoRepository.existsById(id)) {
            throw new MovimientoException("No se encontró movimiento con ID: " + id);
        }
        movimientoRepository.deleteById(id);
    }

    private BigDecimal calcularNuevoSaldo(MovimientoDto movimientoDto, Cuenta cuenta) {
        BigDecimal saldoActual = obtenerSaldoActual(cuenta.getNumeroCuenta());
        
        return switch (movimientoDto.tipo().toUpperCase()) {
            case "DEPOSITO" -> saldoActual.add(movimientoDto.valor());
            case "RETIRO" -> saldoActual.subtract(movimientoDto.valor());
            default -> throw new MovimientoException("Tipo de movimiento no válido: " + movimientoDto.tipo());
        };
    }
}
