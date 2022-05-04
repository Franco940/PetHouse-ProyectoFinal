package com.proyectoFinal.PetHouse.controladores;

import com.proyectoFinal.PetHouse.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio us;

    @PostMapping("/registrar")
    public String guardar(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, 
            @RequestParam String contrasenia, @RequestParam Integer telefonoDeContacto, @RequestParam String localidad, @RequestParam String calleNumero) {
        try {
            us.registrarUsuario(nombre, apellido, email, contrasenia, telefonoDeContacto, localidad, calleNumero);
            
            // Falta agregar un lugar en el html para mostrar este mensaje
            //modelo.put("exito", "Registro exitoso");
        } catch (Exception e) {
            // Falta agregar un lugar en el html para mostrar este mensaje
            //modelo.put("error", "Falta algún dato");
        }finally{
            return "form-registro";
        }
    }
    
    @GetMapping("/registrar")
    public String mostrarFormulario(){
        return "form-registro";
    }

}
