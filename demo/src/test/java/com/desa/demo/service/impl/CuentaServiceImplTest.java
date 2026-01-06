package com.desa.demo.service.impl;

import com.desa.demo.entity.Cliente;
import com.desa.demo.entity.Cuenta;
import com.desa.demo.exception.CuentaException;
import com.desa.demo.mapper.CuentaMapper;
import com.desa.demo.repository.ClienteRepository;
import com.desa.demo.repository.CuentaRepository;
import com.desa.demo.service.dto.CuentaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CuentaServiceImplTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private CuentaMapper cuentaMapper;

    @InjectMocks
    private CuentaServiceImpl cuentaService;

    private Cuenta cuenta;
    private CuentaDto cuentaDto;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan Perez");
        cliente.setEstado(true);

        cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setNumeroCuenta("1234567890");
        cuenta.setSaldo(1000.0);
        cuenta.setCliente(cliente);

    }

    @Test
    void crearCuenta_WhenNumeroCuentaExists_ShouldThrowException() {
        when(cuentaRepository.existsByNumeroCuenta(anyString())).thenReturn(true);

        assertThrows(CuentaException.class, () -> cuentaService.crearCuenta(cuentaDto));
        verify(cuentaRepository, never()).save(any(Cuenta.class));
    }


    @Test
    void obtenerCuentaPorNumero_WhenNotExists_ShouldThrowException() {
        when(cuentaRepository.findByNumeroCuenta(anyString())).thenReturn(Optional.empty());

        assertThrows(CuentaException.class, 
            () -> cuentaService.obtenerCuentaPorNumero("9999999999"));
    }

    @Test
    void eliminarCuenta_WhenExists_ShouldDeleteCuenta() {
        when(cuentaRepository.findByNumeroCuenta(anyString())).thenReturn(Optional.of(cuenta));
        doNothing().when(cuentaRepository).delete(any(Cuenta.class));

        assertDoesNotThrow(() -> cuentaService.eliminarCuenta("1234567890"));
        verify(cuentaRepository, times(1)).delete(any(Cuenta.class));
    }

    @Test
    void obtenerCuentasPorCliente_ShouldReturnCuentas() {
        List<Cuenta> cuentas = List.of(cuenta);
        when(cuentaRepository.findByClienteId(anyLong())).thenReturn(cuentas);

        List<Cuenta> result = cuentaService.obtenerCuentasPorCliente(1L);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(cuentaRepository, times(1)).findByClienteId(1L);
    }
}