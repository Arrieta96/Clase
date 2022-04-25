package com.eventus.eventus.controller;

import com.eventus.eventus.model.Entrada;
import com.eventus.eventus.model.Evento;
import com.eventus.eventus.service.ClienteService;
import com.eventus.eventus.service.EntradaService;
import com.eventus.eventus.service.EventoService;
import com.eventus.eventus.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/entrada")
public class EntradaController {
    @Autowired
    private EntradaService entradaService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping()
    public String entrada(Model model) {

        return"";
    }

    @GetMapping("/create")
    public String entradaCreate(Model model, @PathVariable Integer id) {

        model.addAttribute("entradas",new Entrada());
        clienteService.findById(UserDetailsService.cliente.getIdCliente()).ifPresent(cliente -> model.addAttribute("clientes", cliente));

        return "entrada/entradaEvento";
    }

    @PostMapping("/create/save/{id}")
    public String entradaCreateSave(Model model,Entrada entrada, @PathVariable Integer id) {

        clienteService.findById(UserDetailsService.cliente.getIdCliente()).ifPresent(cliente -> model.addAttribute("clientes", cliente));
        Evento evento= new Evento();
        evento.setIdEvento(id);
        entrada.setEvento(evento);
        entradaService.saveOrUpdate(entrada);
        return "redirect:/home";
    }

}
