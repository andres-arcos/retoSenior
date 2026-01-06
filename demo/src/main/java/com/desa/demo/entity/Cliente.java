package com.desa.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cliente")
public class Cliente extends Persona {
    @Column(nullable = false, unique = true, length = 50)
    private String clienteId;
    @Column(nullable = false, length = 100)
    private String contrasena;
    @Column(nullable = false)
    private Boolean estado = true;
}
