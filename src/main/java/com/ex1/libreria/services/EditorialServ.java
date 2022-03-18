package com.ex1.libreria.services;

import com.ex1.libreria.entities.Editorial;
import com.ex1.libreria.repositories.EditorialRep;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialServ {

@Autowired
private EditorialRep editRep;    
    
public void validator(String nombre) throws Exception{
    
    if(nombre == null || nombre.isEmpty()){
        throw new Exception("Nombre inválido.");
    }
}

@Transactional
public Editorial save(String nombre) throws Exception{
    validator(nombre);
    
   Editorial entity = new Editorial();
   entity.setNombre(nombre);
   
   return editRep.save(entity);
}

@Transactional
public Editorial edit(String id, String nombre) throws Exception{
    
    Optional<Editorial> editOpt = editRep.findById(id);
    
    if(editOpt.isPresent()){
        validator(nombre);
        Editorial ed = editOpt.get();
        ed.setNombre(nombre);
        return editRep.save(ed);
    } else {
    return null;
}
}

@Transactional
public void delete(String id) throws Exception {
    
    Optional<Editorial> editOpt = editRep.findById(id);
    
    if(editOpt.isPresent()){
        editRep.deleteById(id);
    } else {
        throw new Exception("No se pudo eliminar la editorial.");
    }
}

@Transactional(readOnly = true)
public List<Editorial> findAll(){
    return editRep.findAll();      
}

@Transactional(readOnly = true)
public List<Editorial> findByName(String nombre) throws Exception{
    if(nombre != null){
        return editRep.buscarxNombre(nombre);
    } else {
        throw new Exception("No se encontró la editorial con el nombre indicado.");
    }
}
}
