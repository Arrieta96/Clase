package com.eventus.eventus.repository;

import com.eventus.eventus.model.Cliente;
import com.eventus.eventus.model.Entrada;
import com.eventus.eventus.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Integer> {


    @Query("SELECT u FROM Entrada u WHERE u.Evento.idEvento = ?1")
   Entrada findByEventoId(Integer id);


}
