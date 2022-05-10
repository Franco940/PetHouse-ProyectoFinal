package com.proyectoFinal.PetHouse.controladores;

import com.proyectoFinal.PetHouse.entidades.Mascota;
import com.proyectoFinal.PetHouse.entidades.Usuario;
import com.proyectoFinal.PetHouse.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio userServ;

    @PostMapping("/registrar")
    public String guardar(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String email,
            @RequestParam String contrasenia, @RequestParam Integer telefonoDeContacto,
            @RequestParam String localidad, @RequestParam String calleNumero, List<Mascota> mascota) {
        try {
            userServ.registrarUsuario(nombre, apellido, email, contrasenia, telefonoDeContacto, localidad, calleNumero, mascota);

            // Falta agregar un lugar en el html para mostrar este mensaje
            //modelo.put("exito", "Registro exitoso");
        } catch (Exception e) {
            // Falta agregar un lugar en el html para mostrar este mensaje
            //modelo.put("error", "Falta algún dato");
        } finally {
            return "form-registro";
        }
    }

    @GetMapping("/registrar")
    public String mostrarFormulario() {
        return "form-registro";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        List<Usuario> cuidadores = userServ.filtrarUsuariosCuidadores();
        List<Usuario> clientes = userServ.filtrarUsuariosClientes();

        // Falta crear el front para esto
        modelo.addAttribute("cuidador", cuidadores);
        modelo.addAttribute("cliente", clientes);

        modelo.put("usuario", userServ.buscarUsuarioPorId(id));

        return "modificar-usuario";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, @RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String email,
            @RequestParam String contrasenia, @RequestParam Integer telefonoDeContacto,
            @RequestParam String localidad, @RequestParam String calleNumero) {
        try {
            userServ.modificarUsuario(id, nombre, apellido, email, contrasenia, telefonoDeContacto, localidad, calleNumero);

        } catch (Exception e) {
            // Hacer la lógica cuando manejemos errores
        } finally {
            return "modificar-usuario";
        }
    }
}
