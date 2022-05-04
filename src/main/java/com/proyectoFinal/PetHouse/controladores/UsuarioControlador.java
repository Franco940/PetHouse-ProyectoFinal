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
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/registrar")
    public String guardar(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String contrasenia, @RequestParam Integer telefonoDeContacto, @RequestParam String ubicacion) {
        try {
            us.registrarUsuario(nombre, apellido, email, contrasenia, telefonoDeContacto, ubicacion);
            modelo.put("exito", "Registro exitoso");
            return "form-registro";
        } catch (Exception e) {
            modelo.put("error", "Falta algún dato");
            return "form-registro";
        }
    }

    @GetMapping("/registrar")
    public String mostrarFormulario() {
        return "form-registro";
    }
/*
    @PostMapping("/contacto")
    public String comentario(ModelMap modelo, @RequestParam String nombre, @RequestParam String email, @RequestParam String contactoComentario) {
        try {
            us.comentario(nombre, contactoComentario);
            modelo.put("exito", "Comentario enviado exitosamente");
            return "form-contacto";
        } catch (Exception e) {
            modelo.put("error", "Falta algún dato");
            return "form-contacto";
        }
    }

    @GetMapping("/contacto")
    public String mostrarComentario() {
        return "form-contacto";
    }
*/
}
