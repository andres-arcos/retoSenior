package com.desa.demo.mapper;

import com.desa.demo.entity.Movimiento;
import com.desa.demo.service.dto.MovimientoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cuenta", ignore = true)
    @Mapping(target = "fecha", ignore = true)
    @Mapping(target = "saldo", ignore = true)
    Movimiento toEntity(MovimientoDto dto);
    
    @Mapping(target = "numeroCuenta", source = "cuenta.numeroCuenta")
    MovimientoDto toDto(Movimiento movimiento);
    
    void updateEntityFromDto(MovimientoDto movimientoDto, @MappingTarget Movimiento movimiento);
}
