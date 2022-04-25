package com.eventus.eventus.repository;

import com.eventus.eventus.model.Cliente;
import com.eventus.eventus.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    List<Evento> findEventosByCliente(Cliente cliente);


}