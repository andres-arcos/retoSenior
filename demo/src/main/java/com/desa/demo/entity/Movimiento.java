package com.desa.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "movimientos")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    @Column(nullable = false, length = 20)
    private String tipo;
     @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valor;
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal saldo;
    @ManyToOne
    @JoinColumn(name = "numero_cuenta")
    private Cuenta cuenta;
}
