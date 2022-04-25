package com.eventus.eventus.service;

import com.eventus.eventus.model.Cliente;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Getter
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    private final ClienteService clienteService;

    public static Cliente cliente;


    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {

        Cliente currentUser= clienteService.findByCorreo(correo);

        if(currentUser == null){
            throw new UsernameNotFoundException(correo);
        }else {
            cliente=currentUser;
        }



        Set<GrantedAuthority> ga = new HashSet<>();
        ga.add(new SimpleGrantedAuthority("Clientes"));

        return new org.springframework.security.core.userdetails.User(currentUser.getCorreoCliente(), currentUser.getContraseñaCliente(), ga);
    }

}
