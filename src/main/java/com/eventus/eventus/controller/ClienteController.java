package com.eventus.eventus.controller;

import com.eventus.eventus.model.Cliente;
import com.eventus.eventus.service.ClienteService;
import com.eventus.eventus.service.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/cliente")
@Controller
@AllArgsConstructor
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Integer id, Model model) {
        clienteService.findById(UserDetailsService.cliente.getIdCliente()).ifPresent(cliente -> model.addAttribute("clientes", cliente));
        return "cliente/editarCliente";
    }



    @GetMapping("/{id}")
    public String cliente(@PathVariable Integer id, Model model) {


        clienteService.findById(UserDetailsService.cliente.getIdCliente()).ifPresent(cliente -> model.addAttribute("clientes", cliente));
        return "cliente/index";
    }

    @GetMapping("/editar/contra/{id}")
    public String editarContraseña(@PathVariable Integer id, Model model,Cliente cliente) {
    //    clienteService.findById(UserDetailsService.cliente.getIdCliente()).ifPresent(cliente -> model.addAttribute("clientes", cliente));
        model.addAttribute("clientes", cliente);
        return "cliente/editarContra";
    }
    @PostMapping("/editar/cliente/save")
    public String editarClienteSave(Cliente cliente) {
        cliente.setIdCliente(UserDetailsService.cliente.getIdCliente());
        cliente.setContraseñaCliente(UserDetailsService.cliente.getContraseñaCliente());
        clienteService.saveOrUpdate(cliente);
        return  "redirect:/cliente/"+cliente.getIdCliente();
    }

    @PostMapping("/editar/contra/save")
    public String editarContraSave(Cliente cliente) {
         clienteService.findById(UserDetailsService.cliente.getIdCliente()).ifPresent(client -> {
             client.setContraseñaCliente(passwordEncoder.encode(cliente.getContraseñaCliente()));
             clienteService.saveOrUpdate(client);
         });
        return  "redirect:/cliente/"+(UserDetailsService.cliente.getIdCliente());
    }

    @GetMapping("/crear")
    public String crearCliente( Model model,Cliente cliente ) {
        model.addAttribute("clientes", cliente);
        System.out.println("si estoy en crear");
        return "cliente/crearCliente";
    }


    @PostMapping("/crear/save")
    public String crearCliente(Cliente cliente) {
        clienteService.saveOrUpdate(cliente);
        return   "redirect:/login";
    }
}
