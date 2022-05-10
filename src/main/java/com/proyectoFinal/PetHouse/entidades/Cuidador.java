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

    private String aniamlesAptoParaCuidar; // Con el .split podríamos acomodar los animales que puede cuidar
    private Integer tarifa;

    @OneToMany(mappedBy = "cuidador", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Mascota> mascotasCuidando;

    public Cuidador() {
        // Cambiar las cosas. Son todos valores de prueba

        this.descripcion = "Me gusta cuidar mascotas";
        this.trabajosRealizados = 100;
        this.disponible = false;
        this.puntajeTotal = 500;
        this.alta = true; // Luego cambiarlo a false. Lo dejo en true para hacer las pruebas
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

    public String getAniamlesAptoParaCuidar() {
        return aniamlesAptoParaCuidar;
    }

    public void setAniamlesAptoParaCuidar(String aniamlesAptoParaCuidar) {
        this.aniamlesAptoParaCuidar = aniamlesAptoParaCuidar;
    }

    public Integer getTarifa() {
        return tarifa;
    }

    public void setTarifa(Integer tarifa) {
        this.tarifa = tarifa;
    }

    public List<Mascota> getMascotasCuidando() {
        return mascotasCuidando;
    }

    public void setMascotasCuidando(List<Mascota> mascotasCuidando) {
        this.mascotasCuidando = mascotasCuidando;
    }
}
