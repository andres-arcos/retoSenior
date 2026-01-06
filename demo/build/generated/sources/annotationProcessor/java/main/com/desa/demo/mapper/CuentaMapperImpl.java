package com.desa.demo.mapper;

import com.desa.demo.entity.Cliente;
import com.desa.demo.entity.Cuenta;
import com.desa.demo.service.dto.CuentaDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-05T22:10:05-0500",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class CuentaMapperImpl implements CuentaMapper {

    @Override
    public Cuenta toEntity(CuentaDto dto) {
        if ( dto == null ) {
            return null;
        }

        Cuenta cuenta = new Cuenta();

        cuenta.setNumeroCuenta( dto.numeroCuenta() );
        cuenta.setSaldo( dto.saldo() );

        return cuenta;
    }

    @Override
    public CuentaDto toDto(Cuenta cuenta) {
        if ( cuenta == null ) {
            return null;
        }

        Long clienteId = null;
        Long id = null;
        String numeroCuenta = null;
        Double saldo = null;

        clienteId = cuentaClienteId( cuenta );
        id = cuenta.getId();
        numeroCuenta = cuenta.getNumeroCuenta();
        saldo = cuenta.getSaldo();

        String mensaje = null;

        CuentaDto cuentaDto = new CuentaDto( id, clienteId, numeroCuenta, saldo, mensaje );

        return cuentaDto;
    }

    @Override
    public void updateEntityFromDto(CuentaDto cuentaDto, Cuenta cuenta) {
        if ( cuentaDto == null ) {
            return;
        }

        cuenta.setId( cuentaDto.id() );
        cuenta.setNumeroCuenta( cuentaDto.numeroCuenta() );
        cuenta.setSaldo( cuentaDto.saldo() );
    }

    private Long cuentaClienteId(Cuenta cuenta) {
        Cliente cliente = cuenta.getCliente();
        if ( cliente == null ) {
            return null;
        }
        return cliente.getId();
    }
}
