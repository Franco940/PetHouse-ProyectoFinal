package com.proyectoFinal.PetHouse.servicios;

import com.proyectoFinal.PetHouse.entidades.Cliente;
import com.proyectoFinal.PetHouse.entidades.Comentario;
import com.proyectoFinal.PetHouse.entidades.Cuidador;
import com.proyectoFinal.PetHouse.entidades.Mascota;
import com.proyectoFinal.PetHouse.entidades.Usuario;
import com.proyectoFinal.PetHouse.enums.Rol;
import com.proyectoFinal.PetHouse.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio ur;

    @Autowired
    private ClienteServicio clienteServ;

    @Autowired
    private CuidadorServicio cuidadorServ;

    @Transactional
    public void registrarUsuario(String nombre, String apellido, String email, String contrasenia,
            Integer telefonoDeContacto, String localidad, String calleNumero, List<Mascota> mascotas) throws Exception {

        Usuario usuario = new Usuario();

        Cliente cliente = new Cliente();
        Cuidador cuidador = new Cuidador();

        clienteServ.crearCliente(cliente, mascotas);
        /*cuidadorServ.crearCuidador(String descripcion, Integer trabajosRealizados, Integer puntajeTotal,
            boolean disponible, boolean alta, List<Comentario> comentarios, String aniamlesAptoParaCuidar,
            Integer tarifa, List<Mascota> mascotasCuidando);no se exactamente que parametros
                                                             tienen que ir aca, al igual que en el servicio*/

        validaciones(nombre, apellido, email, contrasenia, telefonoDeContacto, calleNumero);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setContrasenia(contrasenia);
        usuario.setTelefonoDeContacto(telefonoDeContacto);
        usuario.setUbicacion(calleNumero + ", " + localidad + ", Buenos Aires, Argentina");
        usuario.setRol(Rol.USER);
        usuario.setCliente(cliente);
        usuario.setCuidador(cuidador);
        ur.save(usuario);
    }

    @Transactional
    public void modificarUsuario(String id, String nombre, String apellido, String email, String contrasenia,
            Integer telefonoDeContacto, String localidad, String calleNumero) throws Exception {

        validaciones(nombre, apellido, email, contrasenia, telefonoDeContacto, calleNumero);
        Usuario usuario = ur.buscarPorId(id);

        if (usuario != null) {
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(email);
            usuario.setContrasenia(contrasenia);
            usuario.setTelefonoDeContacto(telefonoDeContacto);
            usuario.setUbicacion(calleNumero + ", " + localidad + ", Buenos Aires, Argentina");
            ur.save(usuario);
        }
    }

    @Transactional
    public Usuario buscarUsuarioPorId(String id) {
        return ur.buscarPorId(id);
    }

    @Transactional
    public void eliminarUsuario(String id) throws Exception {
        ur.buscarPorId(id);
        ur.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Usuario> filtrarUsuariosCuidadores() {
        List<Usuario> usuarios = ur.findAll();
        List<Usuario> usuariosCuidadores = new ArrayList();

        for (Usuario usuario : usuarios) {
            if (usuario.getCuidador().isAlta()) {
                usuariosCuidadores.add(usuario);
            }
        }

        return usuariosCuidadores;
    }

    @Transactional(readOnly = true)
    public List<Usuario> filtrarUsuariosClientes() {
        List<Usuario> usuarios = ur.findAll();
        List<Usuario> usuariosClientes = new ArrayList();

        for (Usuario usuario : usuarios) {
            usuariosClientes.add(usuario);
        }
        return usuariosClientes;
    }

    private void validaciones(String nombre, String apellido, String email, String contrasenia, Integer telefonoDeContacto, String calleNumero) throws Exception {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no puede estar vacío");
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new Exception("El apellido no puede estar vacío");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new Exception("El email no puede estar vacío");
        }
        if (contrasenia == null || contrasenia.trim().isEmpty()) {
            throw new Exception("La contraseña no puede estar vacía");
        }
        if (telefonoDeContacto == null) {
            throw new Exception("El teléfono no puede ser nulo");
        }
        if (calleNumero == null || calleNumero.trim().isEmpty()) {
            throw new Exception("La calle no puede estar vacía");
        }
    }
}
