package com.desa.demo.mapper;

import com.desa.demo.entity.Cliente;
import com.desa.demo.service.dto.ClienteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mapping(target = "id", ignore = true)
    Cliente toEntity(ClienteDto dto);

    ClienteDto toDto(Cliente cliente);
    void updateEntityFromDto(ClienteDto clienteDto, @MappingTarget Cliente cliente);
}
