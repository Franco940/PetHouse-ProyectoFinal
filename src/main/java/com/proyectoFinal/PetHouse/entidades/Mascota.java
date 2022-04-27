package com.proyectoFinal.PetHouse.entidades;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "mascota")
public class Mascota {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idMascota;
    private String nombre;
    private String tipoAnimal; // Si es perro, gato, loro, etc.
    private String raza;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuidador_idCuidador")
    private Cuidador cuidador;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_idCliente")
    private Cliente cliente;

    public Mascota() {
    }

    public Mascota(String nombre, String tipoAnimal, String raza, Cliente cliente) {
        this.nombre = nombre;
        this.tipoAnimal = tipoAnimal;
        this.raza = raza;
        this.cliente = cliente;
    }

    public String getIdMascota() {
        return idMascota;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Cuidador getCuidador() {
        return cuidador;
    }

    public void setCuidador(Cuidador cuidador) {
        this.cuidador = cuidador;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
