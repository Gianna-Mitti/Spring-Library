package com.ex1.libreria.services;

import com.ex1.libreria.entities.Cliente;
import com.ex1.libreria.repositories.ClienteRep;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServ {

    @Autowired
    private ClienteRep clienteRep;

    
    public void validator(Long dni, String nombre, String apellido, String te ) throws Exception {
        if(dni == null) {
            throw new Exception("Se requiere un DNI.");
        }
        if(nombre == null || nombre.isEmpty()) {
            throw new Exception("Se requiere un nombre.");
        }
        if(apellido == null || apellido.isEmpty()) {
            throw new Exception("Se requiere un apellido.");
        }
        if(te == null || te.isEmpty()) {
            throw new Exception("Se requiere un teléfono.");
        }
    }
    
    @Transactional
    public Cliente save(Long dni, String nombre, String apellido, String te) throws Exception {
        validator(dni, nombre, apellido, te);
        
        Cliente entity = new Cliente();
        entity.setDni(dni);
        entity.setNombre(nombre);
        entity.setApellido(apellido);
        entity.setTe(te);
            
        return clienteRep.save(entity);
    }
    
    @Transactional
    public Cliente edit(String id, Long dni, String nombre, String apellido, String te) throws Exception {
        Optional<Cliente> clienteOpt = clienteRep.findById(id);
        
        if(clienteOpt.isPresent()) {
            validator(dni, nombre, apellido, te);
            Cliente c = clienteOpt.get();
            c.setDni(dni);
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setTe(te);
            return clienteRep.save(c);
        } else {
            return null;
        }
    }
    
    @Transactional
    public void delete(String id) throws Exception {
        Optional<Cliente> clienteOpt = clienteRep.findById(id);
        
        if(clienteOpt.isPresent()) {
            clienteRep.deleteById(id);
        } else {
            throw new Exception("No se pudo eliminar el cliente.");
        }
    }
    
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return clienteRep.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Cliente> findByDni(Long dni) throws Exception{
        if(dni != null) {
            return clienteRep.buscarxDNI(dni);
        } else {
            throw new Exception("No se encontraron clientes con el DNI indicado.");
        }
    }
}
