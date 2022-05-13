package com.proyectoFinal.PetHouse.servicios;

import com.proyectoFinal.PetHouse.entidades.Comentario;
import com.proyectoFinal.PetHouse.entidades.Cuidador;
import com.proyectoFinal.PetHouse.repositorios.CuidadorRepositorio;
import java.util.List;
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
        // Agregar la relación con los comentarios
    }
    
    
    @Transactional
    public void agregarDescripcionYTarifa(Cuidador cuidador, String descripcion, Integer tarifa) throws Exception {
        validaciones(descripcion, tarifa);
        
        cuidador.setDescripcion(descripcion);
        cuidador.setTarifa(tarifa);
        cuidador.setAlta(true);
        
        cuidadorRepo.save(cuidador);
    }

    private void validaciones(String descripcion, Integer tarifa) throws Exception {
        
    }
}
