package com.proyectoFinal.PetHouse.servicios;

import com.proyectoFinal.PetHouse.entidades.Comentario;
import com.proyectoFinal.PetHouse.entidades.Cuidador;
import com.proyectoFinal.PetHouse.entidades.Mascota;
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
    public void crearCuidador(String descripcion, Integer trabajosRealizados, Integer puntajeTotal,
            boolean disponible, boolean alta, List<Comentario> comentarios, String aniamlesAptoParaCuidar,
            Integer tarifa, List<Mascota> mascotasCuidando) throws Exception {
        Cuidador cuidador = new Cuidador();
        cuidador.setDescripcion(descripcion);
        cuidador.setTrabajosRealizados(trabajosRealizados);
        cuidador.setPuntajeTotal(puntajeTotal);
        cuidador.setDisponible(disponible);
        cuidador.setAlta(alta);
        cuidador.setComentarios(comentarios);
        cuidador.setAniamlesAptoParaCuidar(aniamlesAptoParaCuidar);
        cuidador.setTarifa(tarifa);
        cuidador.setMascotasCuidando(mascotasCuidando);
        cuidadorRepo.save(cuidador);
    }

    public void validaciones(String descripcion, Integer trabajosRealizados, Integer puntajeTotal,
            boolean disponible, boolean alta, List<Comentario> comentarios, String aniamlesAptoParaCuidar,
            Integer tarifa, List<Mascota> mascotasCuidando) throws Exception {

    }
}
