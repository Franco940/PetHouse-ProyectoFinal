/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoFinal.PetHouse.controladores;

import com.proyectoFinal.PetHouse.entidades.Cuidador;
import com.proyectoFinal.PetHouse.entidades.Usuario;
import com.proyectoFinal.PetHouse.servicios.ComentarioServicio;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author VIC
 */
@Controller
@RequestMapping("/cuidador")
public class ComentarioControlador {
    @Autowired
    ComentarioServicio cs;
    
    
    @PostMapping("/puntuar")
    public void puntuacion(ModelMap modelo, HttpSession session, PathVariable String id,@RequestParam Comentario comentario, @RequestParam Cuidador cuidador){
        Cuidador cuidador = cs.buscarCuidador(idCuidador);
        puntaje(Cuidador cuidador, Integer trabajosRealizados, Integer puntajeTotal);
        try {
           cs.crearComentario(idCliente, cuidador, comentario);
            
            return "redirect:/cuidador/puntuar";
        } catch (Exception e) {
            modelo.put("fallo", e.getMessage());
            
            return "form-comentario";
        }
        try{
             puntaje(Cuidador cuidador, Integer trabajosRealizados, Integer puntajeTotal);
             return "redirect:/cuidador/puntuar";
             } catch (Exception e) {
            modelo.put("fallo", e.getMessage());
            
            return "form-comentario";
        }
        }
    }
}
