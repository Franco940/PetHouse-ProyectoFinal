package com.proyectoFinal.PetHouse.controladores;

import com.proyectoFinal.PetHouse.entidades.Cuidador;
import com.proyectoFinal.PetHouse.servicios.ComentarioServicio;
import com.proyectoFinal.PetHouse.servicios.CuidadorServicio;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/puntuar")
public class ComentarioControlador {
    
    @Autowired
    private ComentarioServicio comentarioServ;
    
    @Autowired
    private CuidadorServicio cuidadorServ;
    
    
    @GetMapping("/puntuando/{id}")
    public String formComentarioPuntaje(ModelMap modelo, HttpSession session){
        if(session.getAttribute("ROL") != null){
            return "form-puntaje";
        }else{
            return "redirect:/usuario/login";
        }
    }
    
    @PostMapping("/puntuando/{id}")
    public String puntuacion(ModelMap modelo, HttpSession session, @PathVariable String idCuidador ,@RequestParam String comentario, 
            @RequestParam Integer puntaje){
        
        if(session.getAttribute("ROL") != null){
            String idUsuario = session.getAttribute("idUsuario").toString();
            Optional<Cuidador> cuidador = cuidadorServ.buscarCuidador(idCuidador);

            try {
               comentarioServ.crearComentario(idUsuario, cuidador.get(), comentario);
               cuidadorServ.guardarPuntajeYSumaTrabajo(cuidador.get(), puntaje);

                modelo.put("exito", true);
                return "form-puntaje";
            } catch (Exception e) {
                modelo.put("fallo", e.getMessage());

                return "form-puntaje";
            }
        }else{
            return "redirect:/usuario/login";
        }
    }
}
