package com.proyectoFinal.PetHouse.servicios;


import com.proyectoFinal.PetHouse.entidades.Usuario;
import com.proyectoFinal.PetHouse.enums.Role;
import com.proyectoFinal.PetHouse.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Thomas
 */
@Service
public class UsuarioServicio implements UserDetailsService {
    
     @Autowired
    private UsuarioRepositorio ur;
     
    @Transactional
    public Usuario crearUsuario( String nombre, String apellido, String email, String pw1, String pw2, String telefonoDeContacto) throws Exception{

        validar(nombre,apellido,email,pw1,pw2,telefonoDeContacto);
        Usuario usuario= new Usuario();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setTelefonoDeContacto(apellido);
        usuario.setEmail(email);
        usuario.setContrasenia(encoder.encode(pw1));
        usuario.setRole(Role.USER);
        return ur.save(usuario);
        
    }
    
    @Transactional
    public Usuario modificarUsuario(String nombre, String apellido, String id, String email, String pw1, String pw2, String telefonoDeContacto) throws Exception{
        validarModificacion(nombre,apellido,email,pw1,pw2,telefonoDeContacto);
        Usuario u = getOne(id);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (u==null) {
            throw new Exception("No existe un usuario con esa ID");
        }
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setTelefonoDeContacto(telefonoDeContacto);
        u.setEmail(email);
        u.setContrasenia(encoder.encode(pw1));
        u.setRole(u.getRole());
        
        
        return ur.save(u);
    }
    
    @Transactional
    public Usuario getOne(String id){
        return ur.getOne(id);
    }
    
     @Transactional
    public Usuario findByEmail(String email){
        return ur.findByEmail(email);
    }
    
    @Transactional
    public List<Usuario> findAll(){
        return ur.findAll();
    }
    
    @Transactional
    public void cambiarRol(String id) throws Exception{
    
    	Optional<Usuario> respuesta = ur.findById(id);
    	
    	if(respuesta.isPresent()) {
    		
    		Usuario usuario = respuesta.get();
    		
    		if(usuario.getRole().equals(Role.USER)) {
    			
    		usuario.setRole(Role.ADMIN);
    		
    		}else if(usuario.getRole().equals(Role.ADMIN)) {
    			usuario.setRole(Role.USER);
    		}
    	}
    }
    
    public void validarModificacion(String nombre, String apellido, String email, String pw1, String pw2, String telefonoDeContacto) throws Exception{
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("Nombre no puede estar vacio");
        }   
        if (apellido == null || apellido.isEmpty()) {
            throw new Exception("Apellido no puede estar vacio");
        }
        if (telefonoDeContacto == null || telefonoDeContacto.isEmpty()) {
            throw new Exception("Nombre no puede estar vacio");
        }       
        if (email == null || email.isEmpty()) {
            throw new Exception("Email no puede estar vacio");
        }
        if (pw1 == null || pw2 == null || pw1.isEmpty() || pw2.isEmpty()) {
            throw new Exception("Las contraseñas no pueden estar vacias");
        }
        if (!pw1.equals(pw2)) {
            throw new Exception("Las contraseñas no coinciden");
        }
    }
    
    public void validar(String nombre, String apellido,String email, String pw1, String pw2, String telefonoDeContacto) throws Exception{
        if (email == null || email.isEmpty()) {
            throw new Exception("Email no puede estar vacio");
        }
        if (ur.findByEmail(email)!= null) {
            throw new Exception("Ya existe un usuario con ese email");
        }
        if (pw1 == null || pw2 == null || pw1.isEmpty() || pw2.isEmpty()) {
            throw new Exception("Las contraseñas no pueden estar vacias");
        }
        if (!pw1.equals(pw2)) {
            throw new Exception("Las contraseñas no coinciden");
        }
        
         if (nombre == null || nombre.isEmpty()) {
            throw new Exception("Nombre no puede estar vacio");
        }   
        if (apellido == null || apellido.isEmpty()) {
            throw new Exception("Apellido no puede estar vacio");
        }
        if (telefonoDeContacto == null || telefonoDeContacto.isEmpty()) {
            throw new Exception("Nombre no puede estar vacio");
        }       
        
        
    }

    @Transactional
    public void eliminarUsuario(String id){
        Usuario u = getOne(id);
        ur.delete(u);
    }
    
   @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = ur.findByEmail(email);
        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + usuario.getRole());//ROLE_ADMIN O ROLE_USER
            permisos.add(p1); //Un permiso solo agregado, puede haber mas.

            //Esto me permite guardar el OBJETO USUARIO LOG, para luego ser utilizado
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);

            User user = new User(usuario.getEmail(), usuario.getContrasenia(), permisos);
            return user;

        } else {
            return null;
        }
    }
     
    
    
}
