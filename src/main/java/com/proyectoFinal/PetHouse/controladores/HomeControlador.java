package com.proyectoFinal.PetHouse.controladores;

import com.proyectoFinal.PetHouse.entidades.Usuario;
import com.proyectoFinal.PetHouse.servicios.UsuarioServicio;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@RequestMapping("/")
public class HomeControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
    @PostMapping("/login")
    public String comprobarLogin(ModelMap modelo, @RequestParam String email, @RequestParam String contrasenia, HttpSession session){
        try{
            Usuario usuario = usuarioServicio.comprobarLogin(email, contrasenia);
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            session = attr.getRequest().getSession(true);
            
            // Atributos que va a guardar la sesión del usuario
            session.setAttribute("nombre", usuario.getNombre());
            session.setAttribute("apellido", usuario.getApellido());
            session.setAttribute("direccion", usuario.getUbicacion());
            session.setAttribute("ROL", usuario.getRol());
            session.setAttribute("cuidador", usuario.getCuidador().isAlta());
            
            return "redirect:/";
        }catch(Exception e){
            modelo.put("fallo", e.getMessage());
            return "login";
        }
        
    }
}
