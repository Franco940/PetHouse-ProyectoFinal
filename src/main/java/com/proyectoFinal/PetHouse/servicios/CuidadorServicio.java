/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoFinal.PetHouse.servicios;

import com.proyectoFinal.PetHouse.entidades.Cuidador;
import com.proyectoFinal.PetHouse.repositorios.CuidadorRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author VIC
 */
@Service
public class CuidadorServicio {

    @Autowired
    private CuidadorRepositorio cuidadorRepo;

    public void crearCuidador(String descripcion, Integer trabajosRealizados, boolean disponible, Integer puntajeTotal) throws Exception {
        //cámbienle o quítenle algún parametro (y también a la entidad) si así lo consideran
    }

    @Transactional(readOnly = true)
    public List<Cuidador> listarCuidadores() {
        return cuidadorRepo.findAll();
    }
    /*BUSCAR POR ID
    LISTAR
    MODIFICAR
    ELIMINAR
    */
    /*
   VALIDACIONES
     */
}
