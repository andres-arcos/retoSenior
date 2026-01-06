package com.desa.demo.service.impl;

import com.desa.demo.entity.Cliente;
import com.desa.demo.exception.ClienteException;
import com.desa.demo.mapper.ClienteMapper;
import com.desa.demo.repository.ClienteRepository;
import com.desa.demo.service.ClienteService;
import com.desa.demo.service.dto.ClienteDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;


    @Override
    public ClienteDto crearCliente(ClienteDto clienteDto) {
        log.info("Ingresa a crear un cliente {}", clienteDto.nombre());
        boolean res = clienteRepository.existsByIdentificacion(clienteDto.identificacion());
        if (res) {
            throw new ClienteException("Ya existe un cliente con la identificación: ".concat(clienteDto.identificacion()));
        }
        Cliente cliente = clienteRepository.save(clienteMapper.toEntity(clienteDto));

        log.info("salida a crear un cliente {}", cliente.getClienteId());
        return clienteMapper.toDto(cliente);
    }

    @Override
    public ClienteDto obtenerClientePorIdentificacion(final String identificacion) {
        return clienteMapper.toDto(clienteRepository.findByIdentificacionAndEstado(identificacion, true)
                .orElseThrow(() -> new ClienteException("Cliente no encontrado")));
    }

    @Override
    public ClienteDto actualizarCliente(ClienteDto clienteDto) {
        log.info("Ingresa actualizacion de cliente {}", clienteDto.nombre());

        Cliente clienteExistente = clienteRepository.findById(clienteDto.id())
                .orElseThrow(() -> new ClienteException("Cliente no encontrado"));

        clienteMapper.updateEntityFromDto(clienteDto, clienteExistente);
        Cliente updatedCliente = clienteRepository.save(clienteExistente);
        return clienteMapper.toDto(updatedCliente);
    }

    @Override
    public ClienteDto eliminarCliente(ClienteDto clienteDeleteRequestDto) {
        log.info("Ingresa a eliminar un cliente");
        Cliente cliente = clienteRepository.findByIdentificacionAndEstado(clienteDeleteRequestDto.identificacion(), true)
                .orElseThrow(() -> new ClienteException("Cliente no encontrado"));
        cliente.setEstado(false);
        clienteRepository.save(cliente);
        return clienteMapper.toDto(cliente);
    }

    @Override
    public List<Cliente> obtenerClientesPorEstado() {
        return clienteRepository.consultarClientes(true);
    }

}
