package com.eventus.eventus.service;

import com.eventus.eventus.model.Usuario;
import com.eventus.eventus.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements CrudService<Usuario, Integer>{

    @Autowired
    private  UsuarioRepository usuarioRepository;



    @Override
    public Optional<Usuario> findById(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    @Override
    public Optional<List<Usuario>> findAll() {
        return Optional.of(usuarioRepository.findAll());

    }

    @Override
    public Usuario saveOrUpdate(Usuario usuario) {
        return usuarioRepository.save(usuario);

    }

    @Override
    public boolean deleteById(Integer idUsuario) {
        return findById(idUsuario).map((usuario->{
            usuarioRepository.delete(usuario);
            return true;
        })).orElse(false);
    }
}
