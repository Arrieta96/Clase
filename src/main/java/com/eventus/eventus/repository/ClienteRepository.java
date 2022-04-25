package com.eventus.eventus.repository;

import com.eventus.eventus.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Cliente findByCorreoCliente(String correo);
}
