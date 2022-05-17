package com.proyectoFinal.PetHouse.servicios;

import com.proyectoFinal.PetHouse.entidades.Comentario;
import com.proyectoFinal.PetHouse.entidades.Cuidador;
import com.proyectoFinal.PetHouse.repositorios.ComentarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ComentarioServicio {
    
    @Autowired
    private ComentarioRepositorio comentarioRepo;
    
    
    public void crearComentario(String idCliente, Cuidador cuidador, String comentario) throws Exception{
        validarComentario(comentario);
        
        Comentario comentarioObjeto = new Comentario();
        comentarioObjeto.setIdCliente(idCliente);
        comentarioObjeto.setCuidador(cuidador);
        comentarioObjeto.setComentario(comentario);
        
        comentarioRepo.save(comentarioObjeto);
    }
    
    private void validarComentario(String comentario) throws Exception{
        if(comentario == null || comentario.isEmpty()){
            throw new Exception("Por favor, escriba un breve comentario del servicio del cuidador");
        }
    }
    
}
