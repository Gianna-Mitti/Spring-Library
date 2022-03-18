package com.ex1.libreria.services;

import com.ex1.libreria.entities.Autor;
import com.ex1.libreria.repositories.AutorRep;
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
        throw new Exception("Nombre inválido");
    }
}

@Transactional
public Autor save(String nombre) throws Exception{
    validator(nombre);
    Autor entity = new Autor();
    entity.setNombre(nombre);
    
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
        autorRep.deleteById(id);
    } else {
        throw new Exception("No se pudo eliminar el/la autor/a.");
    }
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
