package com.eventus.eventus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AppController {

    @GetMapping()
    public String index(){
        return "login";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("menu")
    public String menu(){
        return "menu";
    }

    @GetMapping("logout")
    public String logout(){
        return "redirect:/login";
    }


}
