package com.desa.demo.service.dto;

public record CuentaDto(
        Long id,
        Long clienteId,
        String numeroCuenta,
        Double saldo,
        String mensaje
) {
    public CuentaDto withMensaje(String mensaje) {
        return new CuentaDto(
                this.id,
                this.clienteId,
                this.numeroCuenta,
                this.saldo,
                mensaje
        );
    }
}
