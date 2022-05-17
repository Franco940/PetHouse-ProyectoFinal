package com.proyectoFinal.PetHouse.repositorios;

import com.proyectoFinal.PetHouse.entidades.Comentario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ComentarioRepositorio extends JpaRepository<Comentario,String>{
    
    @Query("SELECT c FROM Comentario c JOIN Usuario u ON c.usuario.idUsuario = u.idUsuario WHERE c.cuidador.idCuidador = :idC")
    public List<Comentario> buscarPorCuidador(@Param("idC") String id);
}
