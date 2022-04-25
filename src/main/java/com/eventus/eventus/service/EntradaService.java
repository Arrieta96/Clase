package com.eventus.eventus.service;

import com.eventus.eventus.model.Cliente;
import com.eventus.eventus.model.Entrada;
import com.eventus.eventus.model.Evento;
import com.eventus.eventus.repository.EntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaService implements CrudService<Entrada, Integer>{

    @Autowired
    private  EntradaRepository entradaRepository;



    @Override
    public Optional<Entrada> findById(Integer idEntrada) {
        return entradaRepository.findById(idEntrada);
    }

    @Override
    public Optional<List<Entrada>> findAll() {
        return Optional.of(entradaRepository.findAll());

    }

    @Override
    public Entrada saveOrUpdate(Entrada entrada) {
        return entradaRepository.save(entrada);

    }

    @Override
    public boolean deleteById(Integer idEntrada) {
        return findById(idEntrada).map((entrada->{
            entradaRepository.delete(entrada);
            return true;
        })).orElse(false);
    }

    public Entrada findByEvento(Integer id) {
        return entradaRepository.findByEventoId(id);
    }
}
