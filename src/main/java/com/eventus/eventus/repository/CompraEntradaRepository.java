package com.eventus.eventus.repository;

import com.eventus.eventus.model.CompraEntrada;
import com.eventus.eventus.model.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompraEntradaRepository extends JpaRepository<CompraEntrada, Integer> {

    @Query("SELECT u FROM CompraEntrada u WHERE u.Cliente.idCliente = ?1")
    Optional<List<CompraEntrada>> findByIdCliente(Integer id);
}
