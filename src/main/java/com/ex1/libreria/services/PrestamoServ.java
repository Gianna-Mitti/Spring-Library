package com.ex1.libreria.services;

import com.ex1.libreria.entities.Cliente;
import com.ex1.libreria.entities.Libro;
import com.ex1.libreria.entities.Prestamo;
import com.ex1.libreria.repositories.PrestamoRep;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrestamoServ {

    @Autowired
private PrestamoRep presRep;

public void validator(Libro l, Cliente c) throws Exception{
    
    if(l == null) {
        throw new Exception("Debe indicar un libro.");
    }
    if(c == null) {
        throw new Exception("Debe indicar un cliente.");
    }
}

@Transactional
public Prestamo save(Libro l, Cliente c) throws Exception {
    validator(l, c);
    
    Prestamo entity = new Prestamo();
    entity.setLibro(l);
    entity.setCliente(c);
    
    return presRep.save(entity);
}

@Transactional
public Prestamo edit(String id, Libro l, Cliente c) throws Exception {
    
    Optional<Prestamo> presOpt = presRep.findById(id);
    
    if(presOpt.isPresent()) {
        validator(l, c);
        Prestamo p = presOpt.get();
        p.setLibro(l);
        p.setCliente(c);
        return presRep.save(p);
    } else {
        return null;
    }
}

@Transactional
public void delete(String id) throws Exception {
    
    Optional<Prestamo> presOpt = presRep.findById(id);
    
    if(presOpt.isPresent()) {
        presRep.deleteById(id);
    } else {
        throw new Exception("No se pudo eliminar el préstamo.");
    }
}

@Transactional(readOnly = true)
public List<Prestamo> findAll() {
    return presRep.findAll();
}

@Transactional(readOnly = true)
public List<Prestamo> findByBook(String libro) throws Exception{
    if(libro != null) {
        return presRep.buscarxLibro(libro);
    } else {
        throw new Exception("No se encontró el libro indicado.");
    }
}

@Transactional(readOnly = true)
public List<Prestamo> findByClient(String cliente) throws Exception{
    if(cliente != null) {
        return presRep.buscarxCliente(cliente);
    } else {
        throw new Exception("No se encontraron préstamos para el cliente indicado.");
    }
}
}
