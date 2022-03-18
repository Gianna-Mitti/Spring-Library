package com.ex1.libreria.services;

import com.ex1.libreria.entities.Autor;
import com.ex1.libreria.entities.Editorial;
import com.ex1.libreria.entities.Libro;
import com.ex1.libreria.repositories.LibroRep;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServ {

    @Autowired
    private LibroRep libroRep;

    public void validator(String titulo, Integer anio, Integer cant, Integer prestados, Integer restantes, Autor a, Editorial ed) throws Exception {

        if (titulo == null || titulo.isEmpty()) {
            throw new Exception("Título inválido.");
        }
        if (anio == null) {
            throw new Exception("Año inválido.");
        }
        if (cant == null || cant < 0) {
            throw new Exception("Cantidad de ejemplares inválida.");
        }
        if (prestados == null || prestados < 0) {
            throw new Exception("Cantidad de ejemplares prestados inválida.");
        }
        if (restantes == null || restantes < 0) {
            throw new Exception("Cantidad de ejemplares restantes inválida.");
        }
//        if (alta == null){
//            throw new Exception("Fecha de alta inválida.");
//        }
        if (a == null) {
            throw new Exception("Debe indicar un/a autor/a.");
        }
        if (ed == null) {
            throw new Exception("Debe indicar una editorial.");
        }
    }

    @Transactional
    public Libro save(String titulo, Integer anio, Integer cant, Integer prestados, Integer restantes, Autor a, Editorial ed) throws Exception {
        validator(titulo, anio, cant, prestados, restantes, a, ed);
        Libro entity = new Libro();
        entity.setTitulo(titulo);
        entity.setAnio(anio);
        entity.setCant(cant);
        entity.setPrestados(prestados);
        entity.setRestantes(restantes);
        entity.setAlta(new Date());
        entity.setAutor(a);
        entity.setEditorial(ed);

        return libroRep.save(entity);
    }

    @Transactional
    public Libro edit(String id, String titulo, Integer anio, Integer cant, Integer prestados, Integer restantes, Autor a, Editorial ed) throws Exception {

        Optional<Libro> libroOpt = libroRep.findById(id);

        if (libroOpt.isPresent()) {
            validator(titulo, anio, cant, prestados, restantes, a, ed);
            Libro l = libroOpt.get();
            l.setTitulo(titulo);
            l.setAnio(anio);
            l.setCant(cant);
            l.setPrestados(prestados);
            l.setRestantes(restantes);
//            l.setAlta(new Date());
            l.setAutor(a);
            l.setEditorial(ed);
            return libroRep.save(l);
        } else {
            return null;
        }
    }

    @Transactional
    public void delete(String id) throws Exception {
        Optional<Libro> libroOpt = libroRep.findById(id);

        if (libroOpt.isPresent()) {
            libroRep.deleteById(id);
        } else {
            throw new Exception("No se pudo eliminar el libro.");
        }
    }

    @Transactional(readOnly = true)
    public List<Libro> findAll() {
        return libroRep.findAll();
    }

    @Transactional(readOnly = true)
    public Libro findOne(String id) throws Exception {
        Optional<Libro> libroOpt = libroRep.findById(id);

        if (libroOpt.isPresent()) {
            return libroRep.getById(id);
        } else {
            throw new Exception("No se encontró el libro con el id indicado.");
        }
    }

    @Transactional(readOnly = true)
        public List<Libro> findByTitle(String title) throws Exception{
    if(title != null){
        return libroRep.buscarxTitulo(title);
    } else {
       throw new Exception("No se encontró un libro con el título indicado."); 
    }
    }
    
        @Transactional(readOnly = true)
    public List<Libro> findByAuthor(String autor) throws Exception {
        if (autor != null) {
            return libroRep.buscarxAutor(autor);
        } else {
            throw new Exception("No se encontraron libros autor indicado.");
        }
    }

    @Transactional(readOnly = true)
    public List<Libro> findByEd(String ed) throws Exception {
        if (ed != null) {
            return libroRep.buscarxEditorial(ed);
        } else {
            throw new Exception("No se encontraron libros con la editorial indicada.");
        }
    }

}
