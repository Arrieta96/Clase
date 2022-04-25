package com.eventus.eventus.service;

import com.eventus.eventus.model.Cliente;
import com.eventus.eventus.model.Evento;
import com.eventus.eventus.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService implements CrudService<Evento, Integer>{

    @Autowired
    private  EventoRepository eventoRepository;



    @Override
    public Optional<Evento> findById(Integer idEvento) {
        return eventoRepository.findById(idEvento);
    }

    @Override
    public Optional<List<Evento>> findAll() {
        return Optional.of(eventoRepository.findAll());

    }

    @Override
    public Evento saveOrUpdate(Evento evento) {
        return eventoRepository.save(evento);

    }

    @Override
    public boolean deleteById(Integer idEvento) {
        return findById(idEvento).map((evento->{
            eventoRepository.delete(evento);
            return true;
        })).orElse(false);
    }


    public List<Evento> findByCliente(Cliente cliente) {
        return eventoRepository.findEventosByCliente(cliente);
    }


}
