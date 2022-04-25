package com.eventus.eventus.controller;

import com.eventus.eventus.service.ClienteService;
import com.eventus.eventus.service.EventoService;
import com.eventus.eventus.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private EventoService eventoService;
    @Autowired
    private ClienteService clienteService;
    @GetMapping()
    public String eventos(Model model) {
        eventoService.findAll().ifPresent(eventos -> model.addAttribute("eventos", eventos));
        try{
            clienteService.findById(UserDetailsService.cliente.getIdCliente()).ifPresent(cliente -> model.addAttribute("clientes", cliente));

        }
        catch (Exception ex)
        {
            clienteService.findById(1).ifPresent(cliente -> model.addAttribute("clientes", cliente));

        }
        return "home/index";
    }
}
