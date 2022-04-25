package com.eventus.eventus.service;

import com.eventus.eventus.model.Cliente;
import com.eventus.eventus.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService implements CrudService<Cliente, Integer>{

    @Autowired
    private  ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Optional<Cliente> findById(Integer idCliente) {
        return clienteRepository.findById(idCliente);
    }

    @Override
    public Optional<List<Cliente>> findAll() {
        return Optional.of(clienteRepository.findAll());

    }

    @Override
    public Cliente saveOrUpdate(Cliente cliente) {

    try {
        if(cliente.getIdCliente()!=null)
            return clienteRepository.save(cliente);
        else
        {
            cliente.setContraseñaCliente(passwordEncoder.encode(cliente.getContraseñaCliente()));
            return clienteRepository.save(cliente);
        }

    }catch (Exception ex){
        return clienteRepository.save(cliente);
    }

    }

    @Override
    public boolean deleteById(Integer idCliente) {
        return findById(idCliente).map((cliente->{
            clienteRepository.delete(cliente);
            return true;
        })).orElse(false);
    }

    public Cliente findByCorreo(String  correo) {
        return clienteRepository.findByCorreoCliente(correo);
    }
}
