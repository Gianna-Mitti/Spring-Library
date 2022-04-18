package com.ex1.libreria.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Libro {

@Id
@GeneratedValue(generator = "uuid")
@GenericGenerator(name = "uuid", strategy = "uuid2")
private String isbn;
private String titulo;
private Integer anio;
private Integer cant;
private Integer prestados;
private Integer restantes;
private Boolean alta;
//private Date alta;
//private Date baja;
@ManyToOne
private Autor autor;
@ManyToOne
private Editorial editorial;

    public Libro() {
    }

    public Libro(String titulo, Integer anio, Integer cant, Integer prestados, Integer restantes, Boolean alta, Autor autor, Editorial editorial) {
        this.titulo = titulo;
        this.anio = anio;
        this.cant = cant;
        this.prestados = prestados;
        this.restantes = restantes;
        this.alta = alta;
        this.autor = autor;
        this.editorial = editorial;
    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getCant() {
        return cant;
    }

    public void setCant(Integer cant) {
        this.cant = cant;
    }

    public Integer getPrestados() {
        return prestados;
    }

    public void setPrestados(Integer prestados) {
        this.prestados = prestados;
    }

    public Integer getRestantes() {
        return restantes;
    }

    public void setRestantes(Integer restantes) {
        this.restantes = restantes;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }
    
    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "Libro{ " + "ISBN= " + isbn + ", titulo= " + titulo + ", año= " + anio + ", cant= " + cant + ", prestados= " + prestados + ", restantes= " + restantes + ", alta= " + alta + ", autor= " + autor + ", editorial= " + editorial + '}';

}
}