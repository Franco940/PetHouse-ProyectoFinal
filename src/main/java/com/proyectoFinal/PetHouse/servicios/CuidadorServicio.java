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
    public void crearCuidador(Cuidador cuidador) {
        cuidadorRepo.save(cuidador);
    }

    @Transactional
    public void agregarDescripcionYTarifa(Cuidador cuidador, String descripcion, Integer tarifa) throws Exception {
        validaciones(descripcion, tarifa);

        cuidador.setDescripcion(descripcion);
        cuidador.setTarifa(tarifa);
        cuidador.setAlta(true);

        cuidadorRepo.actualizarDatos(cuidador.getIdCuidador(), cuidador.getDescripcion(), cuidador.getTarifa(), true);
    }

    @Transactional
    public void puntaje(Cuidador cuidador, Integer trabajosRealizados, Integer puntajeTotal) {
        cuidador.setPuntajeTotal(cuidador.getPuntajeTotal() + puntaje);
        cuidador.setTrabajosRealizados(cuidador.getTrabajosRealizados()+1);
        cuidadorRepo.actualizarPuntaje(cuidador.getIdCuidador(),cuidador.getPuntajeTotal(),cuidador.getTrabajosRealizados());
    }

    private void validaciones(String descripcion, Integer tarifa) throws Exception {
        if (descripcion == null || descripcion.isEmpty()) {
            throw new Exception("La descripción no puede estar vacía");
        }
        if (tarifa == null || tarifa == 0) {
            throw new Exception("La tarifa no puede ser 0");
        }
    }
}
