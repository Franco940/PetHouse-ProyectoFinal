package com.proyectoFinal.PetHouse.repositorios;

import com.proyectoFinal.PetHouse.entidades.Cuidador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CuidadorRepositorio extends JpaRepository <Cuidador, String> {

    @Modifying 
    @Query("UPDATE Cuidador AS c SET c.puntajeTotal = :puntaje, c.trabajosRealizados = :numero WHERE c.idCuidador = :id")
    public void actualizarPuntajeYTrabajos(@Param("id") String id, @Param("puntaje") Integer puntaje, @Param ("numero") Integer numero);
    
    @Modifying
    @Query("UPDATE Cuidador AS c SET c.descripcion = :descripcion, c.tarifa = :tarifa, c.alta = :alta WHERE c.idCuidador = :id")
    public void actualizarDatos(@Param("id") String id, @Param("descripcion") String descripcion, @Param("tarifa") Integer tarifa, @Param("alta") boolean alta);

    @Query("SELECT x FROM Cuidador x where x.id = :id")
    public Cuidador buscarPorId(@Param("id") String id);
}
