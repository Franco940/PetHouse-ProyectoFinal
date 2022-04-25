package com.proyectoFinal.PetHouse.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class HomeControlador {
    
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
}
