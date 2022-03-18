package com.ex1.libreria.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
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
private String te;

    public Cliente() {
    }

    public Cliente(Long dni, String nombre, String apellido, String te) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.te = te;
    }

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

    public String getTe() {
        return te;
    }

    public void setTe(String te) {
        this.te = te;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id= " + id + ", dni= " + dni + ", nombre= " + nombre + ", apellido= " + apellido + ", te= " + te + '}';
    }


}
