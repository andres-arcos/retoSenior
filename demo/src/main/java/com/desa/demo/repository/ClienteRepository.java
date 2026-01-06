package com.desa.demo.repository;

import com.desa.demo.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByIdentificacionAndEstado(String identificacion, Boolean estado);

    boolean existsByIdentificacion(String identificacion);

    @Query("SELECT c FROM Cliente c WHERE (:estado IS NULL OR c.estado = :estado)")
    List<Cliente> consultarClientes(
            @Param("estado") Boolean estado);
}
