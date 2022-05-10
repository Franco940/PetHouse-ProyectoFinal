/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoFinal.PetHouse.servicios;

import com.proyectoFinal.PetHouse.repositorios.MascotaRepositorio;
import com.proyectoFinal.PetHouse.entidades.Cliente;
import com.proyectoFinal.PetHouse.entidades.Mascota;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author VIC
 */
@Service
public class MascotaServicio {

    @Autowired
    private MascotaRepositorio mr;

    @Transactional
    public Mascota crearMascota(String nombre, String tipoAnimal, String raza, Cliente cliente) throws Exception {
        validaciones(nombre, tipoAnimal, raza, cliente);
        /* mr.buscarMascotaPorCliente(nombre);*/
        Mascota mascota = new Mascota();
        mascota.setNombre(nombre);
        mascota.setTipoAnimal(tipoAnimal);
        mascota.setRaza(raza);
        mascota.setCliente(cliente);
        return mr.save(mascota);
    }

    @Transactional
    public Mascota modificarMascota(String id, String nombre, String tipoAnimal, String raza, Cliente cliente) throws Exception {
        validaciones(nombre, tipoAnimal, raza, cliente);
        Mascota mascota = mr.buscarMascotaPoId(id);
        if (mascota != null) {
            mascota.setNombre(nombre);
            mascota.setTipoAnimal(tipoAnimal);
            mascota.setRaza(raza);
            mascota.setCliente(cliente);
            return mr.save(mascota);

        } else {
            throw new Exception("No existe un libro con ese id");
        }
    }

    @Transactional
    public void eliminarMascota(String idMascota) throws Exception {
        Mascota mascota = mr.buscarMascotaPoId(idMascota);

        if (mascota.getNombre() != null) {
            mr.delete(mascota);
            mr.save(mascota);
        } else {
            throw new Exception("No existe una mascota con el valor solicitado");
        }
    }

    /*@Transactional(readOnly = true)
    public List<Mascota> buscarMascotaPorCliente(String idMascota, String nombre, String tipoAnimal, String raza, Cliente cliente) throws Exception {
        validaciones(nombre, tipoAnimal, raza, cliente);
        return mr.buscarMascotaPorCliente(idMascota);
    }*/

    @Transactional(readOnly = true)
    public List<Mascota> listarMascotas() {
        return mr.findAll();
    }

    public void validaciones(String nombre, String tipoAnimal, String raza, Cliente cliente) throws Exception {
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("La mascota debe tener un nombre");
        }
        if (tipoAnimal == null || tipoAnimal.isEmpty()) {
            throw new Exception("El tipo de animal no puede estar vacío");
        }
        if (raza == null || raza.isEmpty()) {
            throw new Exception("La raza del animal no puede estar vacía");
        }
        if (cliente == null) {
            throw new Exception("La mascota debe tener un dueño. Este campo no puede estar vacío");
        }
    }
}
