package com.proyectoFinal.PetHouse.servicios;

import com.proyectoFinal.PetHouse.entidades.Cuidador;
import com.proyectoFinal.PetHouse.entidades.Usuario;
import com.proyectoFinal.PetHouse.enums.Rol;
import com.proyectoFinal.PetHouse.repositorios.UsuarioRepositorio;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServicio{

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Autowired
    private CuidadorServicio cuidadorServ;
    
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public Usuario registrarUsuario(String nombre, String apellido, String email, String contrasenia,
            Integer telefonoDeContacto, String localidad, String calleNumero, String contrasenia2, MultipartFile imagenDePerfil) throws Exception {
      
        Usuario usuario = new Usuario();
        
        Cuidador cuidador = new Cuidador();
        
        cuidadorServ.crearCuidador(cuidador);
        
        validacionesRegistro(nombre, apellido, email, contrasenia, contrasenia2, 
                telefonoDeContacto, calleNumero,imagenDePerfil);
        
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setContrasenia(encoder.encode(contrasenia2));
        usuario.setTelefonoDeContacto(telefonoDeContacto);
        usuario.setUbicacion(calleNumero + ", " + localidad + ", Buenos Aires, Argentina");
        
        // En la base de datos guardo el nombre de la imagen
        // luego con el nombre guardado busco en el directorio la imagen guardada
        usuario.setFotoDePerfil(guardarImagenLocalmente(imagenDePerfil));

        usuario.setRol(Rol.USER);
        
        
        usuario.setCuidador(cuidador);
        
        return usuarioRepo.save(usuario);
    }
    
    public Usuario comprobarLogin(String email, String contraseniaPlana) throws Exception{
        validarCamposLogin(email, contraseniaPlana);
        
        Usuario usuario = buscarPorEmail(email);
        
        if(usuario != null && (encoder.matches(contraseniaPlana, usuario.getContrasenia()))){
            return usuario;
        }else{
            throw new Exception("Correo o contraseña incorrectos");
        }
    }

    @Transactional
    public void modificarUsuario(String id, String nombre, String apellido, String email,String contrasenia, String contrasenia2,
            Integer telefonoDeContacto, String localidad, String calleNumero) throws Exception {
        
  
        //validaciones(nombre, apellido, email,contrasenia, contrasenia2, telefonoDeContacto, calleNumero);
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
    private Usuario buscarPorEmail(String email){
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
    
    private void validacionesRegistro(String nombre, String apellido, String email,String contrasenia, 
            String contrasenia2, Integer telefonoDeContacto, String calleNumero, MultipartFile imagenDePerfil)throws Exception{
      
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
        if(imagenDePerfil == null || imagenDePerfil.isEmpty()){
            throw new Exception("Debe de subir una imagen de perfil");
        }
    }
    
    private void validarCamposLogin(String nombre, String contrasenia) throws Exception{
        if(nombre == null || nombre.isEmpty()){
            throw new Exception("El correo no puede estar en blanco");
        }
        if(contrasenia == null || contrasenia.isEmpty()){
            throw new Exception("La contraseña no puede estar vacía");
        }
    }
    
    private void validarModificacion(String nombre, String apellido, String email,String contrasenia, String contrasenia2, 
            Integer telefonoDeContacto, String calleNumero) throws Exception{
        
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
    
    private String guardarImagenLocalmente(MultipartFile imagenDePerfil) throws Exception{
        Path directorioImagenes = Paths.get("src//main//resources//static/fotosDePerfil");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        
        try{
            byte[] bytesDeLaImagen = imagenDePerfil.getBytes();
            Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagenDePerfil.getOriginalFilename());
            Files.write(rutaCompleta, bytesDeLaImagen);
            
            // Si se guarda bien la imagen, devuelvo el nombre para guardarlo en la base de datos
            return imagenDePerfil.getOriginalFilename();
        }catch(Exception e){
            throw new Exception("Error -> Clase: UsuarioServicio, Método: guardarImagenLocalmente");
        }
    }
}
