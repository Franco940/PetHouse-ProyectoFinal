package com.proyectoFinal.PetHouse.controladores;

import com.proyectoFinal.PetHouse.entidades.Cliente;
import com.proyectoFinal.PetHouse.entidades.Cuidador;
import com.proyectoFinal.PetHouse.servicios.ClienteServicio;
import com.proyectoFinal.PetHouse.servicios.CuidadorServicio;
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
    private UsuarioServicio us;

    @Autowired
    private CuidadorServicio cuidadorServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @PostMapping("/registrar")
    public String guardar(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, 
            @RequestParam String email,
            @RequestParam String contrasenia, @RequestParam Integer telefonoDeContacto, 
            @RequestParam String localidad, @RequestParam String calleNumero) {
        try {
            us.registrarUsuario(nombre, apellido, email, contrasenia, telefonoDeContacto, localidad, calleNumero);

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
/*falta metodo de listar*/
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        List<Cuidador> cuidadores = cuidadorServicio.listarCuidadores();
        List<Cliente> clientes = clienteServicio.listarClientes();/*metodo listar cliente aun no creado*/
        modelo.addAttribute("cuidador", cuidadores);
        modelo.addAttribute("cliente", clientes);

        modelo.put("usuario", us.buscarUsuarioPorId(id));
        return "modificar-usuario";/*modificar-usuario aun no creado*/
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, @RequestParam String nombre, @RequestParam String apellido, 
            @RequestParam String email,
            @RequestParam String contrasenia, @RequestParam Integer telefonoDeContacto, 
            @RequestParam String localidad, @RequestParam String calleNumero){
        try{
            us.modificarUsuario(id, nombre, apellido, email, contrasenia, telefonoDeContacto, localidad, calleNumero);
        return "redirect:/usuario/listar";
        }catch (Exception e){
            return "modificar-usuario";
        }
    }
}
