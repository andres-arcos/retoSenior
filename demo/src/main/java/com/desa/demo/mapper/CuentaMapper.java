package com.desa.demo.mapper;

import com.desa.demo.entity.Cuenta;
import com.desa.demo.service.dto.CuentaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CuentaMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true) // We'll set this manually in the service
    Cuenta toEntity(CuentaDto dto);
    
    @Mapping(target = "clienteId", source = "cliente.id")
    CuentaDto toDto(Cuenta cuenta);
    
    void updateEntityFromDto(CuentaDto cuentaDto, @MappingTarget Cuenta cuenta);
}
