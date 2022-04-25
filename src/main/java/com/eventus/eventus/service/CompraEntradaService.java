package com.eventus.eventus.service;

import com.eventus.eventus.model.Cliente;
import com.eventus.eventus.model.CompraEntrada;
import com.eventus.eventus.model.Evento;
import com.eventus.eventus.repository.CompraEntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Service
public class CompraEntradaService implements CrudService<CompraEntrada, Integer>{

    @Autowired
    private  CompraEntradaRepository compraEntradaRepository;



    @Override
    public Optional<CompraEntrada> findById(Integer idCompraEntrada) {
        return compraEntradaRepository.findById(idCompraEntrada);
    }

    @Override
    public Optional<List<CompraEntrada>> findAll() {
        return Optional.of(compraEntradaRepository.findAll());

    }

    @Override
    public CompraEntrada saveOrUpdate(CompraEntrada compraEntrada) {
        return compraEntradaRepository.save(compraEntrada);

    }

    @Override
    public boolean deleteById(Integer idCompraEntrada) {
        return findById(idCompraEntrada).map((compraEntrada->{
            compraEntradaRepository.delete(compraEntrada);
            return true;
        })).orElse(false);
    }


    public Optional<List<CompraEntrada>> findByIdCliente(Integer id) {
        return compraEntradaRepository.findByIdCliente(id);
    }

}
