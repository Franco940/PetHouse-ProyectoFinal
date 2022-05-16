package com.proyectoFinal.PetHouse.controladores;

import com.proyectoFinal.PetHouse.entidades.Usuario;
import com.proyectoFinal.PetHouse.servicios.UsuarioServicio;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio userServ;

    @GetMapping("/registrar")
    public String mostrarFormulario(HttpSession session) {
        
        if(session.getAttribute("ROL") == null){
            return "form-registro";
        }else{
            return "redirect:/";
        }
    }
    
    @PostMapping("/registrar")
    public String guardar(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String email,
            @RequestParam String contrasenia,@RequestParam String contrasenia2, @RequestParam Integer telefonoDeContacto, 
            @RequestParam String localidad, @RequestParam String calleNumero,
            @RequestParam MultipartFile imagen) {
      
        try {
            userServ.registrarUsuario(nombre, apellido, email, contrasenia,
             telefonoDeContacto, localidad,  calleNumero, contrasenia2, imagen);
            
            return "redirect:/usuario/login";
        } catch (Exception e) {
            modelo.put("fallo", e.getMessage());
            
            return "form-registro";
        }
    }

    @GetMapping("/login")
    public String login(HttpSession session){
        
        // Si estas logeado te redirijo al home
        if(session.getAttribute("ROL") != null){
            return "redirect:/";
        }else{
            return "login";
        }
    }
    
    @PostMapping("/login")
    public String comprobarLogin(ModelMap modelo, @RequestParam String email, @RequestParam String contrasenia, HttpSession session){
        try{
            Usuario usuario = userServ.comprobarLogin(email, contrasenia);
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            session = attr.getRequest().getSession(true);
            
            // Atributos que va a guardar la sesión del usuario
            session.setAttribute("idUsuario", usuario.getIdUsuario());
            session.setAttribute("nombre", usuario.getNombre());
            session.setAttribute("apellido", usuario.getApellido());
            session.setAttribute("direccion", usuario.getUbicacion());
            session.setAttribute("ROL", usuario.getRol());
            session.setAttribute("cuidador", usuario.getCuidador().isAlta());
            session.setAttribute("fotoPerfil", usuario.getFotoDePerfil());
            
            return "redirect:/";
        }catch(Exception e){
            modelo.put("fallo", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/modificar/{id}")
    public String modificar(HttpSession session, @PathVariable String id, ModelMap modelo) {
        if(session.getAttribute("ROL") !=  null){
            Usuario usuario = userServ.buscarUsuarioPorId(id);

            modelo.addAttribute("usuario", usuario);

            return "form-modificarDatos";
        }else{
            return "redirect:/usuario/login";
        }
    }

    @PostMapping("/modificar/{id}")
    public String modificar(ModelMap modelo, HttpSession session, @PathVariable String id, @RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String email, @RequestParam String contrasenia, @RequestParam String contrasenia2, 
            @RequestParam Integer telefonoDeContacto, @RequestParam String localidad, @RequestParam String calleNumero,
            @RequestParam MultipartFile imagenDePerfil) {
      
        try {
            Usuario usuario = userServ.modificarUsuario(id, nombre, apellido, email, contrasenia, contrasenia2, 
                    telefonoDeContacto, localidad, calleNumero, imagenDePerfil);
            
            // Cambio los atributos de la session para que se actualizen los datos cuando thymeleaf
            // llama a la variable session
            session.setAttribute("nombre", usuario.getNombre());
            session.setAttribute("apellido", usuario.getApellido());
            session.setAttribute("direccion", usuario.getUbicacion());
            session.setAttribute("cuidador", usuario.getCuidador().isAlta());
            session.setAttribute("fotoPerfil", usuario.getFotoDePerfil());
            
            return "redirect:/usuario/modificar/" + id;
        } catch (Exception e) {
            modelo.put("fallo", e.getMessage());
            return "form-modificarDatos";
        }
    }
}
