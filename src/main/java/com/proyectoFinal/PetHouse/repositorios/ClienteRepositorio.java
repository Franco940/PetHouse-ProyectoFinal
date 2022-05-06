package com.proyectoFinal.PetHouse.repositorios;

import com.proyectoFinal.PetHouse.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, String>{
    
}
