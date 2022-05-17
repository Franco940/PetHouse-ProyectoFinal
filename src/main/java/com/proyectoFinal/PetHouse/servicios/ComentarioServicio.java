package com.proyectoFinal.PetHouse.servicios;

import com.proyectoFinal.PetHouse.entidades.Comentario;
import com.proyectoFinal.PetHouse.entidades.Cuidador;
import com.proyectoFinal.PetHouse.entidades.Usuario;
import com.proyectoFinal.PetHouse.repositorios.ComentarioRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ComentarioServicio {
    
    @Autowired
    private ComentarioRepositorio comentarioRepo;
    
    @Transactional(readOnly = true)
    public List<Comentario> buscarComentarios(String idCuidador){
        List<Comentario> comentarios = comentarioRepo.buscarPorCuidador(idCuidador);
        
        return comentarios;
    }
    
    @Transactional
    public void crearComentario(Usuario usuarioQuienHaceElComentario, Cuidador cuidador, String comentario) throws Exception{
        validarComentario(comentario);
        
        Comentario comentarioObjeto = new Comentario();
        comentarioObjeto.setUsuario(usuarioQuienHaceElComentario);
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
