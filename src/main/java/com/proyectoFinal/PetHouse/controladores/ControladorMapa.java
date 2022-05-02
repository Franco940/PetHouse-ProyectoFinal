package com.proyectoFinal.PetHouse.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mapa")
public class ControladorMapa {
    
    @GetMapping("mostrar")
    public String index(){
        return "mapa.html";
    }
}
