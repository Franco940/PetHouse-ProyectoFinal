/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoFinal.PetHouse.servicios;

import com.proyectoFinal.PetHouse.entidades.Cliente;
import com.proyectoFinal.PetHouse.entidades.Cuidador;
import com.proyectoFinal.PetHouse.entidades.Usuario;
import com.proyectoFinal.PetHouse.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author VIC
 */
@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio ur;

    @Transactional
    public void registrarUsuario(String nombre, String apellido, String email, String contrasenia, Integer telefonoDeContacto, String ubicacion, Cuidador cuidador, Cliente cliente) throws Exception {
        Usuario usuario = new Usuario();
        validaciones(nombre, apellido, email, contrasenia, telefonoDeContacto, ubicacion);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setContrasenia(contrasenia);
        usuario.setTelefonoDeContacto(telefonoDeContacto);
        usuario.setUbicacion(ubicacion);
        usuario.setCuidador(cuidador);
        usuario.setCliente(cliente);
        ur.save(usuario);
    }
}
