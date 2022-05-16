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
@Table(name = "cuidador")
public class Cuidador {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idCuidador;
    private String descripcion;
    private Integer trabajosRealizados;
    private Integer puntajeTotal;
    private boolean disponible;
    private boolean alta;

    @OneToMany(mappedBy = "cuidador", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Comentario> comentarios;
    
    private Integer tarifa;

    public Cuidador() {
        this.descripcion = null;
        this.trabajosRealizados = 0;
        this.disponible = true;
        this.puntajeTotal = 0;
        this.alta = false;
        this.tarifa = 0;
    }

    public String getIdCuidador() {
        return idCuidador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getTrabajosRealizados() {
        return trabajosRealizados;
    }

    public void setTrabajosRealizados(Integer trabajosRealizados) {
        this.trabajosRealizados = trabajosRealizados;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Integer getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(Integer puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getTarifa() {
        return tarifa;
    }

    public void setTarifa(Integer tarifa) {
        this.tarifa = tarifa;
    }
}
