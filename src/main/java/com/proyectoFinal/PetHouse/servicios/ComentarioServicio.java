/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoFinal.PetHouse.servicios;

import com.proyectoFinal.PetHouse.entidades.Comentario;
import com.proyectoFinal.PetHouse.entidades.Cuidador;
import com.proyectoFinal.PetHouse.repositorios.ComentarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author VIC
 */
@Service
public class ComentarioServicio {
    
    @Autowired
    ComentarioRepositorio cr;
    
    
    public void crearComentario(String idCliente, Cuidador cuidador, String comentario){
        Comentario comentario = new Comentario();
        comentario.setIdCliente(idCliente);
        comentario.setCuidador(cuidador);
        comentario.setComentario(comentario);
        cr.save(comentario);
    }
    
}
