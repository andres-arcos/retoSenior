package com.desa.demo.mapper;

import com.desa.demo.entity.Cuenta;
import com.desa.demo.entity.Movimiento;
import com.desa.demo.service.dto.MovimientoDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-05T22:31:11-0500",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class MovimientoMapperImpl implements MovimientoMapper {

    @Override
    public Movimiento toEntity(MovimientoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Movimiento movimiento = new Movimiento();

        movimiento.setTipo( dto.tipo() );
        movimiento.setValor( dto.valor() );

        return movimiento;
    }

    @Override
    public MovimientoDto toDto(Movimiento movimiento) {
        if ( movimiento == null ) {
            return null;
        }

        String numeroCuenta = null;
        Long id = null;
        LocalDateTime fecha = null;
        String tipo = null;
        BigDecimal valor = null;
        BigDecimal saldo = null;

        numeroCuenta = movimientoCuentaNumeroCuenta( movimiento );
        id = movimiento.getId();
        fecha = movimiento.getFecha();
        tipo = movimiento.getTipo();
        valor = movimiento.getValor();
        saldo = movimiento.getSaldo();

        String mensaje = null;

        MovimientoDto movimientoDto = new MovimientoDto( id, fecha, tipo, valor, saldo, numeroCuenta, mensaje );

        return movimientoDto;
    }

    @Override
    public void updateEntityFromDto(MovimientoDto movimientoDto, Movimiento movimiento) {
        if ( movimientoDto == null ) {
            return;
        }

        movimiento.setId( movimientoDto.id() );
        movimiento.setFecha( movimientoDto.fecha() );
        movimiento.setTipo( movimientoDto.tipo() );
        movimiento.setValor( movimientoDto.valor() );
        movimiento.setSaldo( movimientoDto.saldo() );
    }

    private String movimientoCuentaNumeroCuenta(Movimiento movimiento) {
        Cuenta cuenta = movimiento.getCuenta();
        if ( cuenta == null ) {
            return null;
        }
        return cuenta.getNumeroCuenta();
    }
}
