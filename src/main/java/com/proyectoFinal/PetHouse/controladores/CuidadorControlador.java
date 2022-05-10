package com.proyectoFinal.PetHouse.controladores;

import com.proyectoFinal.PetHouse.entidades.Usuario;
import com.proyectoFinal.PetHouse.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/cuidador")
public class CuidadorControlador {

    @Autowired
    private UsuarioServicio userServ;

    @GetMapping("/lista")
    public String listarCuidadores(ModelMap modelo) {
        List<Usuario> usuariosCuidadores = userServ.filtrarUsuariosCuidadores();
        
        modelo.addAttribute("usuariosCuidadores", usuariosCuidadores);

        return "list-cuidador";
    }
    
    @GetMapping("/informacion/{id}")
    public String contactoConCuidador(ModelMap modelo, @PathVariable String id){
        Usuario usuarioCuidador = userServ.buscarUsuarioPorId(id);
        
        
        modelo.put("cuidador", usuarioCuidador);
        
        return "informacion-cuidador.html";
    }
}
