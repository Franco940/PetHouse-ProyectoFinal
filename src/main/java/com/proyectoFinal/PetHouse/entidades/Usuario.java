package com.proyectoFinal.PetHouse.entidades;

import com.proyectoFinal.PetHouse.enums.Rol;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    private Integer telefonoDeContacto;
    private String ubicacion;
    
    @Enumerated(EnumType.STRING)
    private Rol rol;
    
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

    public Integer getTelefonoDeContacto() {
        return telefonoDeContacto;
    }

    public void setTelefonoDeContacto(Integer telefonoDeContacto) {
        this.telefonoDeContacto = telefonoDeContacto;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
