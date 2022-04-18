package com.ex1.libreria.services;

import com.ex1.libreria.entities.Autor;
import com.ex1.libreria.repositories.AutorRep;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServ {

@Autowired
private AutorRep autorRep;

public void validator (String nombre) throws Exception{
    if (nombre == null || nombre.isEmpty()){
        throw new Exception("El nombre no puede estar vacío.");
    }
}

@Transactional
public Autor save(String nombre) throws Exception{
    validator(nombre);
    Autor entity = new Autor();
    entity.setNombre(nombre);
    entity.setAlta(true);
    
    return autorRep.save(entity);
}

@Transactional
public Autor edit(String id, String nombre) throws Exception {
    Optional<Autor> autorOpt = autorRep.findById(id);
    
    if(autorOpt.isPresent()){
        validator(nombre);
        Autor a = autorOpt.get();
        a.setNombre(nombre);
        return autorRep.save(a);
    } else {
        return null;
    }
}

@Transactional
public void delete(String id) throws Exception{
    Optional<Autor> autorOpt = autorRep.findById(id);
    
    if(autorOpt.isPresent()){
        Autor a = autorOpt.get();
        a.setAlta(false);
//        autorRep.deleteById(id);
    } else {
        throw new Exception("No se pudo dar de baja el/la autor/a.");
    }
}

@Transactional
public void alta(String id) throws Exception{
    Optional<Autor> autorOpt = autorRep.findById(id);
    
    if(autorOpt.isPresent()){
        Autor a = autorOpt.get();
        a.setAlta(true);
    } else {
        throw new Exception("No se pudo dar de alta el libro.");
    }
}

    @Transactional(readOnly=true)
    public Autor getOne(String id){
        return autorRep.getOne(id);
    }

@Transactional(readOnly = true)
public List<Autor> findAll(){
    return autorRep.findAll();
}

@Transactional(readOnly = true)
public List<Autor> findByName(String nombre) throws Exception{
    if(nombre != null){
        return autorRep.buscarxNombre(nombre);
    } else {
        throw new Exception("No se encontró un/a autor/a con el nombre indicado.");
    }
}
}
