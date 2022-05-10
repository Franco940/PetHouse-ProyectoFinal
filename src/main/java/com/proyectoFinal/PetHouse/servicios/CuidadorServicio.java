package com.proyectoFinal.PetHouse.servicios;

import com.proyectoFinal.PetHouse.entidades.Cuidador;
import com.proyectoFinal.PetHouse.repositorios.CuidadorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CuidadorServicio {
    
    @Autowired
    private CuidadorRepositorio cuidadorRepo;

    @Transactional
    public void crearCuidador(Cuidador cuidador){
        cuidadorRepo.save(cuidador);
    }
}
