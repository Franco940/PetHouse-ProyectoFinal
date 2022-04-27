package com.proyectoFinal.PetHouse.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasenia;
    private String telefonoDeContacto;
    
    /*
        Al hacer una tabla por cada entidad, necesitamos tenerlas relacionadas
    para poder acceder a los atributos de usuario siendo un cliente o cuidador.
    
    Esto también permite que un cliente sea cuidador o un cuidador sea cliente
    al mismo tiempo
    */
    @OneToOne
    private Cuidador cuidador;
    
    @OneToOne 
    private Cliente cliente;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String email, String contrasenia, String telefonoDeContacto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenia = contrasenia;
        this.telefonoDeContacto = telefonoDeContacto;
    }

    public String getIdUsuario() {
        return idUsuario;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTelefonoDeContacto() {
        return telefonoDeContacto;
    }

    public void setTelefonoDeContacto(String telefonoDeContacto) {
        this.telefonoDeContacto = telefonoDeContacto;
    }
}
