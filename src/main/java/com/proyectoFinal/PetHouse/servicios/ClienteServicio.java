package com.proyectoFinal.PetHouse.servicios;

import com.proyectoFinal.PetHouse.entidades.Cliente;
import com.proyectoFinal.PetHouse.entidades.Mascota;
import com.proyectoFinal.PetHouse.repositorios.ClienteRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServicio {
    
    @Autowired
    private ClienteRepositorio clienteRepo;
    
    @Transactional
    public void crearCliente(Cliente cliente, List<Mascota> mascotas){
        
        cliente.setMascotas(mascotas); //creo que hay un problema para relacionar las mascotas con el cliente
                                       // o no estoy implementando una buena logica en el metodo
        clienteRepo.save(cliente);
    }
    
    @Transactional
    public void modificarCliente(Cliente cliente, List<Mascota> mascotas) throws Exception{
        validaciones(mascotas);
        cliente.setMascotas(mascotas);
    }
    @Transactional(readOnly = true)
    public Cliente buscarClientePorId(String id){
        return clienteRepo.buscarPorId(id);
    }
         
    public void validaciones(List<Mascota> mascotas) throws Exception{
        if (mascotas == null || mascotas.isEmpty()){
            throw new Exception("El cliente debe tener al menos una mascota");
        }
    }
}
