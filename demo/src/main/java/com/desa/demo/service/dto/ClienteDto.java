package com.desa.demo.service.dto;

public record ClienteDto(
        Long id,
        String nombre,
        String clienteId,
        String contrasena,
        String genero,
        Integer edad,
        String identificacion,
        String direccion,
        String telefono,
        Boolean estado,
        String mensaje
) {

    public ClienteDto withMensaje(String mensaje) {
        return new ClienteDto(
                this.id,
                this.nombre,
                this.clienteId,
                this.contrasena,
                this.genero,
                this.edad,
                this.identificacion,
                this.direccion,
                this.telefono,
                this.estado,
                mensaje
        );
    }
}
