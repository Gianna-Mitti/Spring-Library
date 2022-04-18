package com.ex1.libreria.entities;

import com.ex1.libreria.enums.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
private String id;
    @Column(unique = true)
private Long dni;
private String nombre;
private String apellido;
private String email;
private String te;

private String password;

@Enumerated(EnumType.STRING)
private Role rol;
private Boolean alta;
        
    public Cliente() {
    }

//    public Cliente(Long dni, String nombre, String apellido, String email, String te) {
//        this.dni = dni;
//        this.nombre = nombre;
//        this.apellido = apellido;
//        this.email = email;
//        this.te = te;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
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
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getTe() {
        return te;
    }

    public void setTe(String te) {
        this.te = te;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Role getRol() {
        return rol;
    }
    
    public void setRol(Role rol) {
        this.rol = rol;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }
    
    @Override
    public String toString() {
        return "Cliente{" + "id= " + id + ", dni= " + dni + ", nombre= " + nombre + ", apellido= " + apellido + ", email= " + email + ", te= " + te + '}';
    }


}
