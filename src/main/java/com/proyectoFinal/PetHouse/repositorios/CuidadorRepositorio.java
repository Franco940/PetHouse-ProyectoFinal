/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoFinal.PetHouse.repositorios;

import com.proyectoFinal.PetHouse.entidades.Cuidador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author VIC
 */
@Repository
public interface CuidadorRepositorio extends JpaRepository <Cuidador, String> {
    
     @Query("SELECT x FROM Cuidador x where x.id = :id")
    public Cuidador buscarPorId(@Param("id") String id);
    
}