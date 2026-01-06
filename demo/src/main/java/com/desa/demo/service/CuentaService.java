package com.desa.demo.service;

import com.desa.demo.entity.Cuenta;
import com.desa.demo.service.dto.CuentaDto;

import java.util.List;

public interface CuentaService {

    /**
     * Crea una nueva cuenta
     * @param cuentaDto Datos de la cuenta a crear
     * @return Cuenta creada
     */
    CuentaDto crearCuenta(CuentaDto cuentaDto);

    /**
     * Obtiene una cuenta por su número de cuenta
     * @param numeroCuenta Número de cuenta a buscar
     * @return Cuenta encontrada
     */
    CuentaDto obtenerCuentaPorNumero(String numeroCuenta);

    /**
     * Actualiza los datos de una cuenta
     * @param cuentaDto Datos actualizados de la cuenta
     * @return Cuenta actualizada
     */
    CuentaDto actualizarCuenta(CuentaDto cuentaDto);

    /**
     * Elimina una cuenta por su número de cuenta
     * @param numeroCuenta Número de cuenta a eliminar
     */
    void eliminarCuenta(String numeroCuenta);

    /**
     * Obtiene todas las cuentas de un cliente
     * @param clienteId ID del cliente
     * @return Lista de cuentas del cliente
     */
    List<Cuenta> obtenerCuentasPorCliente(Long clienteId);
}
