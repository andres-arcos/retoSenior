package com.desa.demo.service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovimientoDto(
        Long id,
        LocalDateTime fecha,
        String tipo,
        BigDecimal valor,
        BigDecimal saldo,
        String numeroCuenta,
        String mensaje
) {
    public MovimientoDto withMensaje(String mensaje) {
        return new MovimientoDto(
                this.id,
                this.fecha,
                this.tipo,
                this.valor,
                this.saldo,
                this.numeroCuenta,
                mensaje
        );
    }
}
