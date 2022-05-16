package com.proyectoFinal.PetHouse.controladores;

import com.proyectoFinal.PetHouse.entidades.Coordenadas;
import com.proyectoFinal.PetHouse.entidades.Usuario;
import com.proyectoFinal.PetHouse.servicios.UsuarioServicio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
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
    
    @Autowired
    private ApiMapaControlador apiMapaControlador;

    @GetMapping("/lista")
    public String listarCuidadores(ModelMap modelo, HttpSession session) {
        
        // Si no está logeado lo redirijo al login
        if(session.getAttribute("ROL") != null){

            List<Usuario> usuariosCuidadores = userServ.filtrarUsuariosCuidadores();

            modelo.addAttribute("usuariosCuidadores", usuariosCuidadores);

            return "list-cuidador";
            
        }else{
            return "redirect:/usuario/login";
        }
    }
    
    @GetMapping("/informacion/{id}")
    public String contactoConCuidador(ModelMap modelo, @PathVariable String id){
        Usuario usuarioCuidador = userServ.buscarUsuarioPorId(id);
        
        
        modelo.put("cuidador", usuarioCuidador);
        
        return "informacion-cuidador.html";
    }
    
    
    @GetMapping("/filtro/{distanciaFiltro}/{dirUsuario}")
    public String filtroDistancias(ModelMap modelo, @PathVariable String distanciaFiltro, @PathVariable String dirUsuario,
            HttpSession session){
        
        // Si no está logeado, no se hace el calculo si es pedido
        if(session.getAttribute("ROL") != null){
            List<Usuario> usuariosCuidadores = userServ.filtrarUsuariosCuidadores();
            Coordenadas coordenadaUsuario = apiMapaControlador.buscarCoordenadas(dirUsuario);

            List<Usuario> usuariosCumplenFiltro = new ArrayList();

            for (Usuario usuario : usuariosCuidadores) {
                Coordenadas usuarioCuidador = apiMapaControlador.buscarCoordenadas(usuario.getUbicacion());

                Double distanciaEntreUbicaciones = saberDistanciaEntreLasUbicaciones(coordenadaUsuario, usuarioCuidador);
                
                if(distanciaEntreUbicaciones <= Double.valueOf(distanciaFiltro)){
                    usuariosCumplenFiltro.add(usuario);
                }
            }

            modelo.addAttribute("usuariosCuidadores", usuariosCumplenFiltro);
            return "list-cuidadores-filtro";
        }else{
            return "redirect:/usuario/login";
        }
    }
    
    // En este método se aplica la fórmula de Haversine para saber la distancia
    // entre dos puntos en una esfera (o sea, la tierra)
    private Double saberDistanciaEntreLasUbicaciones(Coordenadas usuario, Coordenadas cuidador){
        Double latUsuario = Double.valueOf(usuario.getLat());
        Double lngUsuario = Double.valueOf(usuario.getLng());
        
        Double latCuidador = Double.valueOf(cuidador.getLat());
        Double lngCuidador = Double.valueOf(cuidador.getLng());
        
        final Double radioDeLaTierra = 6378.137;
       
        Double latRadian = radian(latCuidador - latUsuario);
        Double lngRadian = radian(lngCuidador - lngUsuario);
        
        Double calculoComplejo1 = Math.sin(latRadian / 2) * Math.sin(latRadian / 2) +
                Math.cos(radian(latCuidador)) * Math.sin(lngRadian / 2) * Math.sin(lngRadian / 2);
        
        Double calculoComplejo2 = 2 * Math.atan2(Math.sqrt(calculoComplejo1), Math.sqrt(1 - calculoComplejo1));
              
        // La distancia en metros
        Double distanciaEntreUbicaciones = radioDeLaTierra * calculoComplejo2 * 1000;
        return distanciaEntreUbicaciones;
    }
    
    private Double radian(Double numero){
        return (numero * Math.PI) / 180;
    }
}
