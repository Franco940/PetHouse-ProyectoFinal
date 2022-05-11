/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoFinal.PetHouse.repositorios;

import com.proyectoFinal.PetHouse.entidades.Mascota;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author VIC
 */
@Repository
public interface MascotaRepositorio extends JpaRepository<Mascota, List> {
    /*
    @Query("SELECT x FROM Mascota x WHERE x.cliente.mascota = :cliente")
    public List<Mascota> buscarMascotaPorCliente(@Param("cliente") String cliente);
    */

    @Query("SELECT x FROM Mascota x WHERE x.idMascota = :idMascota")
    public Mascota buscarMascotaPoId(@Param("idMascota") String idMascota);
}
