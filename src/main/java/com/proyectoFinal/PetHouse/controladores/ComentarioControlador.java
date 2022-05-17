package com.proyectoFinal.PetHouse.controladores;

import com.proyectoFinal.PetHouse.entidades.Comentario;
import com.proyectoFinal.PetHouse.entidades.Cuidador;
import com.proyectoFinal.PetHouse.entidades.Usuario;
import com.proyectoFinal.PetHouse.servicios.ComentarioServicio;
import com.proyectoFinal.PetHouse.servicios.CuidadorServicio;
import com.proyectoFinal.PetHouse.servicios.UsuarioServicio;
import java.util.List;
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
    private UsuarioServicio userServ;
    
    @Autowired
    private CuidadorServicio cuidadorServ;
    
    
    @GetMapping("/ver-comentarios")
    public String verComentarios(ModelMap modelo, HttpSession session){
        if(session.getAttribute("ROL") != null){
            List<Comentario> comentarios = comentarioServ.buscarComentarios(session.getAttribute("cuidadorID").toString());

            modelo.put("comentarios", comentarios);
            return "lista-comentarios";
        }else{
            return "redirect:/usuario/login";
        }
    }
    
    @GetMapping("/puntuando/{idCuidador}/{error}")
    public String formComentarioPuntaje(ModelMap modelo, HttpSession session, @PathVariable String idCuidador, @PathVariable String error){
        if(session.getAttribute("ROL") != null){
            
            if(!error.equals("false")){
                modelo.put("cuidador", idCuidador);
                modelo.put("respuesta", "Hubo un error al momento de cargar la calificacion.");
                return "form-calificar";
            }else{
                modelo.put("cuidador", idCuidador);
                return "form-calificar";
            }
        }else{
            return "redirect:/usuario/login";
        }
    }
    
    @PostMapping("/puntuando")
    public String puntuacion(ModelMap modelo, HttpSession session, @RequestParam String idCuidador, @RequestParam String comentario, 
            @RequestParam String puntaje){
        
        if(session.getAttribute("ROL") != null){
            String idUsuario = session.getAttribute("idUsuario").toString();
            Optional<Cuidador> cuidador = cuidadorServ.buscarCuidador(idCuidador);
            Usuario usuarioQuienHaceElComentario = userServ.buscarUsuarioPorId(idUsuario);

            try {
               comentarioServ.crearComentario(usuarioQuienHaceElComentario, cuidador.get(), comentario);
               cuidadorServ.guardarPuntajeYSumaTrabajo(cuidador.get(), puntaje);

                modelo.put("respuesta", "Se ha calificado con éxito al cuidador.");
                
                return "redirect:/cuidador/lista";
            } catch (Exception e) {
                modelo.put("respuesta", e.getMessage());

                return "redirect:/puntuar/puntuando/" + idCuidador + "/true";
            }
        }else{
            return "redirect:/usuario/login";
        }
    }
}
