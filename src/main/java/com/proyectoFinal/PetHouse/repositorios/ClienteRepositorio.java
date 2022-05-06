package com.proyectoFinal.PetHouse.repositorios;

import com.proyectoFinal.PetHouse.entidades.Cliente;
import com.proyectoFinal.PetHouse.entidades.Cuidador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepositorio extends JpaRepository <Cliente, String> {
    @Query("SELECT y FROM Cliente y where y.id = :id")
    public Cliente buscarPorId(@Param("id") String id);
}
