package com.desa.demo.mapper;

import com.desa.demo.entity.Cliente;
import com.desa.demo.service.dto.ClienteDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-05T21:40:27-0500",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public Cliente toEntity(ClienteDto dto) {
        if ( dto == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setNombre( dto.nombre() );
        cliente.setGenero( dto.genero() );
        cliente.setEdad( dto.edad() );
        cliente.setIdentificacion( dto.identificacion() );
        cliente.setDireccion( dto.direccion() );
        cliente.setTelefono( dto.telefono() );
        cliente.setClienteId( dto.clienteId() );
        cliente.setContrasena( dto.contrasena() );
        cliente.setEstado( dto.estado() );

        return cliente;
    }

    @Override
    public ClienteDto toDto(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        Long id = null;
        String nombre = null;
        String clienteId = null;
        String contrasena = null;
        String genero = null;
        Integer edad = null;
        String identificacion = null;
        String direccion = null;
        String telefono = null;
        Boolean estado = null;

        id = cliente.getId();
        nombre = cliente.getNombre();
        clienteId = cliente.getClienteId();
        contrasena = cliente.getContrasena();
        genero = cliente.getGenero();
        edad = cliente.getEdad();
        identificacion = cliente.getIdentificacion();
        direccion = cliente.getDireccion();
        telefono = cliente.getTelefono();
        estado = cliente.getEstado();

        String mensaje = null;

        ClienteDto clienteDto = new ClienteDto( id, nombre, clienteId, contrasena, genero, edad, identificacion, direccion, telefono, estado, mensaje );

        return clienteDto;
    }

    @Override
    public void updateEntityFromDto(ClienteDto clienteDto, Cliente cliente) {
        if ( clienteDto == null ) {
            return;
        }

        cliente.setId( clienteDto.id() );
        cliente.setNombre( clienteDto.nombre() );
        cliente.setGenero( clienteDto.genero() );
        cliente.setEdad( clienteDto.edad() );
        cliente.setIdentificacion( clienteDto.identificacion() );
        cliente.setDireccion( clienteDto.direccion() );
        cliente.setTelefono( clienteDto.telefono() );
        cliente.setClienteId( clienteDto.clienteId() );
        cliente.setContrasena( clienteDto.contrasena() );
        cliente.setEstado( clienteDto.estado() );
    }
}
