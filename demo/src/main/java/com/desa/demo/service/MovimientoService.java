package com.desa.demo.service;

import com.desa.demo.entity.Movimiento;
import com.desa.demo.service.dto.MovimientoDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface MovimientoService {

    /**
     * Crea un nuevo movimiento
     * @param movimientoDto Datos del movimiento a crear
     * @return Movimiento creado
     */
    MovimientoDto crearMovimiento(MovimientoDto movimientoDto);
    
    /**
     * Obtiene un movimiento por su ID
     * @param id ID del movimiento a buscar
     * @return Movimiento encontrado
     */
    MovimientoDto obtenerMovimientoPorId(Long id);
    
    /**
     * Obtiene todos los movimientos de una cuenta
     * @param numeroCuenta Número de cuenta
     * @return Lista de movimientos
     */
    List<Movimiento> obtenerMovimientosPorCuenta(String numeroCuenta);
    
    /**
     * Obtiene los movimientos de un cliente en un rango de fechas
     * @param clienteId ID del cliente
     * @param fechaInicio Fecha de inicio
     * @param fechaFin Fecha de fin
     * @return Lista de movimientos
     */
    List<Movimiento> obtenerMovimientosPorClienteYFechas(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin);
    
    /**
     * Obtiene el saldo actual de una cuenta
     * @param numeroCuenta Número de cuenta
     * @return Saldo actual
     */
    BigDecimal obtenerSaldoActual(String numeroCuenta);
    
    /**
     * Elimina un movimiento
     * @param id ID del movimiento a eliminar
     */
    void eliminarMovimiento(Long id);
}
