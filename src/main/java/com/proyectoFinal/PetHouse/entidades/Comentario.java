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
@Table(name = "comentario")
public class Comentario {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idComentario;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cuidador_idCuidador")
    private Cuidador cuidador;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_idUsuario")
    private Usuario usuario;
    
    private String comentario;

    public Comentario() {
   
    }

    public String getIdComentario() {
        return idComentario;
    }
    
    public Cuidador getCuidador() {
        return cuidador;
    }

    public void setCuidador(Cuidador cuidador) {
        this.cuidador = cuidador;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
