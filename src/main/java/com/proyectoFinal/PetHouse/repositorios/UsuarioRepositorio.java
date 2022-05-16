package com.proyectoFinal.PetHouse.repositorios;

import com.proyectoFinal.PetHouse.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    
    @Modifying
    @Query("UPDATE Usuario AS u SET u.nombre = :nombre, u.apellido = :apellido, u.email = :email, "
            + "u.contrasenia = :contrasenia, u.telefonoDeContacto = :telefono, u.ubicacion = :ubicacion, "
            + "u.fotoDePerfil = :foto WHERE u.idUsuario = :id")
    public void actualizar(@Param("id") String id, @Param("nombre") String nombre, @Param("apellido") String apellido, 
            @Param("email") String email, @Param("contrasenia") String contrasenia, @Param("telefono") Integer telefono, 
            @Param("ubicacion") String ubicacion, @Param("foto") String foto);
    
    @Query("SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario")
    public Usuario buscarPorId(@Param("idUsuario") String idUsuario);
    
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario findByEmail(@Param("email") String email);
}
