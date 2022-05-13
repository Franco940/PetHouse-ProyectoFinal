package com.proyectoFinal.PetHouse.servicios;

import com.proyectoFinal.PetHouse.entidades.Cuidador;
import com.proyectoFinal.PetHouse.entidades.Usuario;
import com.proyectoFinal.PetHouse.enums.Rol;
import com.proyectoFinal.PetHouse.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService{

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Autowired
    private CuidadorServicio cuidadorServ;
    
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public Usuario registrarUsuario(String nombre, String apellido, String email, String contrasenia,
            Integer telefonoDeContacto, String localidad, String calleNumero, String contrasenia2) throws Exception {
      
        Usuario usuario = new Usuario();
        
        Cuidador cuidador = new Cuidador();
        
        cuidadorServ.crearCuidador(cuidador);
        
        validaciones(nombre, apellido, email,contrasenia, contrasenia2, telefonoDeContacto, calleNumero);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setContrasenia(encoder.encode(contrasenia2));
        usuario.setTelefonoDeContacto(telefonoDeContacto);
        usuario.setUbicacion(calleNumero + ", " + localidad + ", Buenos Aires, Argentina");
        usuario.setRol(Rol.USER);
        
        
        usuario.setCuidador(cuidador);
        
        return usuarioRepo.save(usuario);
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByEmail(email);
        
        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + usuario.getRol());//ROLE_ADMIN O ROLE_USER
            permisos.add(p1); //Un permiso solo agregado, puede haber mas.

            /*
             //Esto me permite guardar el OBJETO USUARIO LOG, para luego ser utilizado
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            
            // Atributos que va a guardar la sesión del usuario
            session.setAttribute("nombre", usuario.getNombre());
            session.setAttribute("apellido", usuario.getApellido());
            session.setAttribute("direccion", usuario.getUbicacion());
            session.setAttribute("cuidador", usuario.getCuidador().isAlta());
            */
            
            UserDetails user = new User(usuario.getEmail(), usuario.getContrasenia(), permisos);

            return user;
        } else {
            throw new UsernameNotFoundException("Correo o contraseña incorrectos");
        }
    }
    

    @Transactional
    public void modificarUsuario(String id, String nombre, String apellido, String email,String contrasenia, String contrasenia2,
            Integer telefonoDeContacto, String localidad, String calleNumero) throws Exception {
        
  
        validaciones(nombre, apellido, email,contrasenia, contrasenia2, telefonoDeContacto, calleNumero);
        Usuario usuario = usuarioRepo.buscarPorId(id);
         
        if (usuario != null) {
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(email);
            usuario.setContrasenia(encoder.encode(contrasenia));
            usuario.setTelefonoDeContacto(telefonoDeContacto);
            usuario.setUbicacion(calleNumero + ", " + localidad + ", Buenos Aires, Argentina");
            usuario.setRol(usuario.getRol());
            usuarioRepo.save(usuario);
        }
    }

    @Transactional
    public Usuario buscarUsuarioPorId(String id) {
        return usuarioRepo.buscarPorId(id);
    }

    @Transactional(readOnly=true)
    public Usuario findByEmail(String email){
        return usuarioRepo.findByEmail(email);
    }
    
    @Transactional
    public void eliminarUsuario(String id) throws Exception {
        usuarioRepo.buscarPorId(id);
        usuarioRepo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Usuario> filtrarUsuariosCuidadores() {
        List<Usuario> usuarios = usuarioRepo.findAll();
        List<Usuario> usuariosCuidadores = new ArrayList();

        for (Usuario usuario : usuarios) {
            if (usuario.getCuidador().isAlta()) {
                usuariosCuidadores.add(usuario);
            }
        }

        return usuariosCuidadores;
    }
    
    private void validaciones(String nombre, String apellido, String email,String contrasenia, String contrasenia2, Integer telefonoDeContacto, String calleNumero)throws Exception{
      
        if(nombre == null || nombre.trim().isEmpty()){
            throw new Exception("El nombre no puede estar vacío");
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new Exception("El apellido no puede estar vacío");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new Exception("El email no puede estar vacío");
        }
        if (contrasenia == null || contrasenia2 == null || contrasenia.isEmpty() || contrasenia2.isEmpty()) {
            throw new Exception("La contraseña no puede estar vacía");         
        }
         if (!contrasenia.equals(contrasenia2)) {
            throw new Exception("Las contraseñas no coinciden");
        }
        if (telefonoDeContacto == null) {
            throw new Exception("El teléfono no puede ser nulo");
        }
        if (calleNumero == null || calleNumero.trim().isEmpty()) {
            throw new Exception("La calle no puede estar vacía");
        }       
    }
    
    private void validarModificacion(String nombre, String apellido, String email,String contrasenia, String contrasenia2, Integer telefonoDeContacto, String calleNumero) throws Exception{
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("Nombre no puede estar vacio");
        }   
        if (apellido == null || apellido.isEmpty()) {
            throw new Exception("Apellido no puede estar vacio");
        }
        if (telefonoDeContacto == null) {
            throw new Exception("Nombre no puede estar vacio");
        }       
        if (email == null || email.isEmpty()) {
            throw new Exception("Email no puede estar vacio");
        }
        if (contrasenia == null || contrasenia2 == null || contrasenia.isEmpty() || contrasenia2.isEmpty()) {
            throw new Exception("Las contraseñas no pueden estar vacias");
        }
        if (!contrasenia.equals(contrasenia2)) {
            throw new Exception("Las contraseñas no coinciden");
        }    
        if (calleNumero == null || calleNumero.trim().isEmpty()) {
            throw new Exception("La calle no puede estar vacía");
        }   
    }
}
