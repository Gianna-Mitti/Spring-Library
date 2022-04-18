package com.ex1.libreria.services;

import com.ex1.libreria.entities.Autor;
import com.ex1.libreria.entities.Editorial;
import com.ex1.libreria.entities.Libro;
import com.ex1.libreria.repositories.AutorRep;
import com.ex1.libreria.repositories.EditorialRep;
import com.ex1.libreria.repositories.LibroRep;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServ {

    @Autowired
    private LibroRep libroRep;

    @Autowired
    private AutorRep autorRep;
    
    @Autowired
    private EditorialRep editRep;
    
    public void validator(String titulo, Integer anio, Integer cant, Integer prestados, Integer restantes, Autor a, Editorial ed) throws Exception {

        if (titulo == null || titulo.isEmpty()) {
            throw new Exception("El título no puede estar vacío.");
        }
        if (anio == null) {
            throw new Exception("El año no puede estar vacío.");
        }
        if (cant == null || cant < 0) {
            throw new Exception("La cantidad de ejemplares no puede estar vacía.");
        }
        if (prestados == null || prestados < 0) {
            throw new Exception("La cantidad de ejemplares prestados no puede estar vacía.");
        }
        if (restantes == null || restantes < 0) {
            throw new Exception("La cantidad de ejemplares restantes no puede estar vacía.");
        }

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
        entity.setAlta(true);
        
        Autor au = autorRep.getById(a.getId());
        
        if(au == null){
          throw new Exception("No se encontró el/la autor/a.");
        }
        entity.setAutor(au);
        
        Editorial edi = editRep.getById(ed.getId());
        
        if(ed == null){
          throw new Exception("No se encontró la editorial.");
        }
        entity.setEditorial(edi);

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
            
           Autor au = autorRep.getById(a.getId());
        
        if(au == null){
          throw new Exception("No se encontró el/la autor/a.");
        }
        l.setAutor(au);
        
        Editorial edi = editRep.getById(ed.getId());
        
        if(ed == null){
          throw new Exception("No se encontró la editorial.");
        }
        l.setEditorial(edi);  
           
            return libroRep.save(l);
        } else {
            return null;
        }
    }

    @Transactional
    public void delete(String id) throws Exception {
        Optional<Libro> libroOpt = libroRep.findById(id);

        if (libroOpt.isPresent()) {
            Libro l = libroOpt.get();
            l.setAlta(false);
//            libroRep.deleteById(id);
        } else {
            throw new Exception("No se pudo dar de baja el libro.");
        }
    }

    @Transactional
public void alta(String id) throws Exception{
    Optional<Libro> libroOpt = libroRep.findById(id);
    
    if(libroOpt.isPresent()){
        Libro l = libroOpt.get();
        l.setAlta(true);
    } else {
        throw new Exception("No se pudo dar de alta el libro.");
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
            throw new Exception("No se encontraron libros con el/la autor/a indicado/a.");
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
