package com.desa.demo.service.impl;

import com.desa.demo.entity.Cuenta;
import com.desa.demo.entity.Movimiento;
import com.desa.demo.exception.MovimientoException;
import com.desa.demo.mapper.MovimientoMapper;
import com.desa.demo.repository.CuentaRepository;
import com.desa.demo.repository.MovimientoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovimientoServiceImplTest {

    @Mock
    private MovimientoRepository movimientoRepository;

    @Mock
    private CuentaRepository cuentaRepository;

    @Mock
    private MovimientoMapper movimientoMapper;

    @InjectMocks
    private MovimientoServiceImpl movimientoService;

    private Movimiento movimiento;
    private Cuenta cuenta;

    @BeforeEach
    void setUp() {
        cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setNumeroCuenta("1234567890");
        cuenta.setSaldo(1000.0);

        movimiento = new Movimiento();
        movimiento.setId(1L);
        movimiento.setTipo("DEPOSITO");
        movimiento.setValor(BigDecimal.valueOf(500));
        movimiento.setSaldo(BigDecimal.valueOf(1500));
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setCuenta(cuenta);

     
    }


    @Test
    void obtenerMovimientoPorId_WhenNotExists_ShouldThrowException() {
        when(movimientoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(MovimientoException.class, 
            () -> movimientoService.obtenerMovimientoPorId(999L));
    }

    @Test
    void obtenerMovimientosPorCuenta_ShouldReturnMovimientos() {
        List<Movimiento> movimientos = List.of(movimiento);
        when(movimientoRepository.findByNumeroCuenta(anyString())).thenReturn(movimientos);

        List<Movimiento> result = movimientoService.obtenerMovimientosPorCuenta("1234567890");

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(movimientoRepository, times(1)).findByNumeroCuenta("1234567890");
    }

    @Test
    void eliminarMovimiento_WhenExists_ShouldDeleteMovimiento() {
        when(movimientoRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(movimientoRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> movimientoService.eliminarMovimiento(1L));
        verify(movimientoRepository, times(1)).deleteById(1L);
    }

    @Test
    void eliminarMovimiento_WhenNotExists_ShouldThrowException() {
        when(movimientoRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(MovimientoException.class, 
            () -> movimientoService.eliminarMovimiento(999L));
        verify(movimientoRepository, never()).deleteById(anyLong());
    }

    @Test
    void obtenerSaldoActual_WhenNoMovements_ShouldReturnZero() {
        when(movimientoRepository.findFirstByCuentaNumeroCuentaOrderByFechaDesc(anyString()))
            .thenReturn(Optional.empty());

        BigDecimal saldo = movimientoService.obtenerSaldoActual("1234567890");

        assertEquals(BigDecimal.ZERO, saldo);
    }
}