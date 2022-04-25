package com.eventus.eventus.controller;

import com.eventus.eventus.model.Cliente;
import com.eventus.eventus.model.Entrada;
import com.eventus.eventus.model.Evento;
import com.eventus.eventus.service.ClienteService;
import com.eventus.eventus.service.EventoService;
import com.eventus.eventus.service.UserDetailsService;
import com.eventus.eventus.service.util.CargarFotos;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/evento")
public class EventoController {
    @Autowired
    private EventoService eventoService;
    @Autowired
    private ClienteService clienteService;

    private final CargarFotos cargarFotos;

    @GetMapping("/crear")
    public String agregarEvento(Model model) {
        model.addAttribute("eventos", new Evento());
        clienteService.findById(UserDetailsService.cliente.getIdCliente()).ifPresent(cliente -> model.addAttribute("clientes", cliente));
        return "evento/crearEvento";
    }

    @PostMapping("/crear/save")
    public String agregarEventoSave( Evento evento,Model model) {

        evento.setCliente(UserDetailsService.cliente);
        eventoService.saveOrUpdate(evento);
        clienteService.findById(UserDetailsService.cliente.getIdCliente()).ifPresent(cliente -> model.addAttribute("clientes", cliente));
        model.addAttribute("eventos",evento);
        return  "evento/crearEventoFoto";
    }
    @PostMapping("/update/save")
    public String updaterEventoSave( Evento evento,Model model) {
        evento.setCliente(UserDetailsService.cliente);
        eventoService.saveOrUpdate(evento);
        clienteService.findById(UserDetailsService.cliente.getIdCliente()).ifPresent(cliente -> model.addAttribute("clientes", cliente));
        model.addAttribute("eventos",evento);
        return  "evento/crearEventoFoto";
    }


    @PostMapping("/crear/foto/save/{id}")
    public String agregarEventoSave( @RequestParam("camposEventos") MultipartFile camposEventos,@RequestParam("portadaEvento") MultipartFile portadaEvento,@PathVariable Integer id,Model model){



        log.info("Nombre original :" +camposEventos.getOriginalFilename());
        log.info("Nombre original :" +portadaEvento.getOriginalFilename());
try{

     if(cargarFotos.uploadFileToS3(camposEventos) && cargarFotos.uploadFileToS3(portadaEvento)){
            log.info("Se envio al S3");

         eventoService.findById(id).ifPresent(evento -> {
             evento.setPortadaEvento("fotoEventos/"+camposEventos.getOriginalFilename());
             evento.setCamposEventos("fotoEventos/"+portadaEvento.getOriginalFilename());
             eventoService.saveOrUpdate(evento);
         });
        }else {
            log.error("No se se cargo lasfoto");
        }
}catch (Exception ex){

     ex.getMessage();
}



        model.addAttribute("eventoId",id);
        System.out.println(id+"id del evento");
        //Para las entradas
        clienteService.findById(UserDetailsService.cliente.getIdCliente()).ifPresent(cliente -> model.addAttribute("clientes", cliente));
        model.addAttribute("entradas",new Entrada());
        return  "entrada/entradaEvento";

    }

    @GetMapping()
    public String eventos(Model model) {

        eventoService.findAll().ifPresent(eventos -> model.addAttribute("eventos", eventos));
        clienteService.findById(UserDetailsService.cliente.getIdCliente()).ifPresent(cliente -> model.addAttribute("clientes", cliente));
        return "evento/index";
    }


    @GetMapping("/eventosCliente")
    public String misEventos(Model model, Cliente cliente) {

        System.out.println("1");
        cliente.setIdCliente(UserDetailsService.cliente.getIdCliente());
        final List<Evento> eventosCliente = eventoService.findByCliente(cliente);
        model.addAttribute("eventosCliente", eventosCliente);
        clienteService.findById(UserDetailsService.cliente.getIdCliente()).ifPresent(x -> model.addAttribute("clientes", x));
        return "evento/misEventos";
    }

    @GetMapping("/{id}")
    public String eventoPorId(@PathVariable Integer id, Model model) {

        clienteService.findById(UserDetailsService.cliente.getIdCliente()).ifPresent(cliente -> model.addAttribute("clientes", cliente));
        eventoService.findById(id).ifPresent(eventos -> model.addAttribute("eventos", eventos));
        return "evento/verEvento";
    }


}
