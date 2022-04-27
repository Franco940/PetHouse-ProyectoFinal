package com.proyectoFinal.PetHouse.entidades;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idCliente;
    
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, 
            cascade = CascadeType.ALL)
    private List<Mascota> mascotas;
    private String dirección;

    public Cliente() {
    }

    public Cliente(List<Mascota> mascotas, String dirección) {
        this.mascotas = mascotas;
        this.dirección = dirección;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }
}
