/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoFinal.PetHouse.controladores;

import com.proyectoFinal.PetHouse.entidades.Cuidador;
import com.proyectoFinal.PetHouse.servicios.CuidadorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author VIC
 */
@Controller
@RequestMapping("/cuidador")
public class CuidadorControlador {

    @Autowired
    private CuidadorServicio cuidadorServicio;

    @GetMapping("/lista")
    public String listarCuidadores(ModelMap modelo) {
        List<Cuidador> cuidadores = cuidadorServicio.listarCuidadores();

        modelo.addAttribute("cuidadores", cuidadores);

        return "list-cuidadores";
    }
}
