/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoFinal.PetHouse.controladores;

import com.proyectoFinal.PetHouse.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author VIC
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio us;

    @GetMapping("/registrar")
    public String guardar(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String contrasenia, @RequestParam Integer telefonoDeContacto, @RequestParam String ubicacion) {
        try {
            us.registrarUsuario(nombre, apellido, email, contrasenia, telefonoDeContacto, ubicacion);
            modelo.put("exito", "Registro exitoso");
            return "registrar-usuario";
        } catch (Exception e) {
            modelo.put("error", "Falta algún dato");
            return "registrar-usuario";
        }
    }

}