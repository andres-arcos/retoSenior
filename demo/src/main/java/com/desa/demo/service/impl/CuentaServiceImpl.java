package com.desa.demo.service.impl;

import com.desa.demo.entity.Cliente;
import com.desa.demo.entity.Cuenta;
import com.desa.demo.exception.ClienteException;
import com.desa.demo.exception.CuentaException;
import com.desa.demo.mapper.CuentaMapper;
import com.desa.demo.repository.ClienteRepository;
import com.desa.demo.repository.CuentaRepository;
import com.desa.demo.service.CuentaService;
import com.desa.demo.service.dto.CuentaDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;
    private final CuentaMapper cuentaMapper;

    @Override
    @Transactional
    public CuentaDto crearCuenta(CuentaDto cuentaDto) {
        log.info("Creando cuenta para el cliente ID: {}", cuentaDto.clienteId());
        
        if (cuentaRepository.existsByNumeroCuenta(cuentaDto.numeroCuenta())) {
            throw new CuentaException("Ya existe una cuenta con el número: " + cuentaDto.numeroCuenta());
        }
        
        Cliente cliente = clienteRepository.findById(cuentaDto.clienteId())
                .orElseThrow(() -> new ClienteException("Cliente no encontrado con ID: " + cuentaDto.clienteId()));
        
        Cuenta cuenta = cuentaMapper.toEntity(cuentaDto);
        cuenta.setCliente(cliente);
        
        Cuenta cuentaCreada = cuentaRepository.save(cuenta);
        log.info("Cuenta creada exitosamente con ID: {}", cuentaCreada.getId());
        
        return cuentaMapper.toDto(cuentaCreada);
    }

    @Override
    public CuentaDto obtenerCuentaPorNumero(String numeroCuenta) {
        log.info("Buscando cuenta con número: {}", numeroCuenta);
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new CuentaException("No se encontró cuenta con número: " + numeroCuenta));
        return cuentaMapper.toDto(cuenta);
    }

    @Override
    @Transactional
    public CuentaDto actualizarCuenta(CuentaDto cuentaDto) {
        log.info("Actualizando cuenta con ID: {}", cuentaDto.id());
        
        Cuenta cuentaExistente = cuentaRepository.findById(cuentaDto.id())
                .orElseThrow(() -> new CuentaException("No se encontró cuenta con ID: " + cuentaDto.id()));
        
        cuentaMapper.updateEntityFromDto(cuentaDto, cuentaExistente);
        
        if (cuentaDto.clienteId() != null && !cuentaExistente.getCliente().getId().equals(cuentaDto.clienteId())) {
            Cliente nuevoCliente = clienteRepository.findById(cuentaDto.clienteId())
                    .orElseThrow(() -> new ClienteException("No se encontró el cliente con ID: " + cuentaDto.clienteId()));
            cuentaExistente.setCliente(nuevoCliente);
        }
        
        Cuenta cuentaActualizada = cuentaRepository.save(cuentaExistente);
        log.info("Cuenta actualizada exitosamente con ID: {}", cuentaActualizada.getId());
        
        return cuentaMapper.toDto(cuentaActualizada);
    }

    @Override
    @Transactional
    public void eliminarCuenta(String numeroCuenta) {
        log.info("Eliminando cuenta con número: {}", numeroCuenta);
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new CuentaException("No se encontró cuenta con número: " + numeroCuenta));
        
        cuentaRepository.delete(cuenta);
        log.info("Cuenta eliminada exitosamente con número: {}", numeroCuenta);
    }

    @Override
    public List<Cuenta> obtenerCuentasPorCliente(Long clienteId) {
        log.info("Obteniendo cuentas para el cliente ID: {}", clienteId);
        return cuentaRepository.findByClienteId(clienteId);
    }
}
