package com.eventus.eventus.controller;

import com.eventus.eventus.model.Cliente;
import com.eventus.eventus.model.CompraEntrada;
import com.eventus.eventus.model.Entrada;
import com.eventus.eventus.model.Evento;
import com.eventus.eventus.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
@RequestMapping("/comprar")
public class CompraController {
    @Autowired
    private EventoService eventoService;
    @Autowired
    private CompraEntradaService compraEntradaService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EntradaService entradaService;

    @GetMapping()
    public String compras(Model model){
        clienteService.findById(UserDetailsService.cliente.getIdCliente()).ifPresent(cliente -> model.addAttribute("clientes", cliente));

        compraEntradaService.findByIdCliente(UserDetailsService.cliente.getIdCliente()).ifPresent(entradas -> model.addAttribute("entradas", entradas));

     return "compra/misCompras";
    }


    @GetMapping("/{id}")
    public String eventoPorId(@PathVariable Integer id, Model model) {

        clienteService.findById(UserDetailsService.cliente.getIdCliente()).ifPresent(cliente -> model.addAttribute("clientes", cliente));
        eventoService.findById(id).ifPresent(eventos -> model.addAttribute("eventos", eventos));


        model.addAttribute("entradas", entradaService.findByEvento(id));

        return "compra/comprarEntradaEvento";
    }

    @PostMapping("entrada/save")
    public String comprarEntradaSave(Entrada entrada, Model model) {

        clienteService.findById(UserDetailsService.cliente.getIdCliente()).ifPresent(cliente -> model.addAttribute("clientes", cliente));


        CompraEntrada compraEntrada= new CompraEntrada();

        Cliente cliente= new Cliente();
        cliente.setIdCliente(UserDetailsService.cliente.getIdCliente());

        compraEntrada.setFechaCompra(new SimpleDateFormat("yyyy/MM/dd ").format(Calendar.getInstance().getTime()));
        compraEntrada.setEntrada(entrada);
        compraEntrada.setCliente(cliente);

        compraEntradaService.saveOrUpdate(compraEntrada);

        return "redirect:/home";
    }
}
