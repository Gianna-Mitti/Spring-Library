package com.ex1.libreria.services;

import com.ex1.libreria.entities.Cliente;
import com.ex1.libreria.enums.Role;
import com.ex1.libreria.repositories.ClienteRep;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class ClienteServ implements UserDetailsService {

    @Autowired
    private ClienteRep clienteRep;
    
    public void validator(Long dni, String nombre, String apellido, String email, String te, String pw1, String pw2) throws Exception {
        if(dni == null) {
            throw new Exception("Se requiere un DNI.");
        }
        if(clienteRep.buscarxDNI(dni)!=null) {
            throw new Exception("Ya existe un usuario registrado con ese DNI.");
        }
        if(nombre == null || nombre.isEmpty()) {
            throw new Exception("Se requiere un nombre.");
        }
        if(apellido == null || apellido.isEmpty()) {
            throw new Exception("Se requiere un apellido.");
        }
        if(email == null || email.isEmpty()) {
            throw new Exception("Se requiere un e-mail.");
        }
        if(clienteRep.buscarxEmail(email)!=null) {
            throw new Exception("Ya existe un usuario con ese e-mail.");
        }
        if(te == null || te.isEmpty()) {
            throw new Exception("Se requiere un teléfono.");
        }
        if(pw1 == null || pw2 == null || pw1.isEmpty() || pw2.isEmpty()) {
            throw new Exception("Las contraseñas no pueden estar vacías.");
        }
        if(!pw1.equals(pw2)) {
            throw new Exception("Las contraseñas no coinciden.");
        }
    }
    
    public void validatorMod(Long dni, String nombre, String apellido, String email, String te, String pw1, String pw2) throws Exception {
        if(dni == null) {
            throw new Exception("Se requiere un DNI.");
        }
        if(nombre == null || nombre.isEmpty()) {
            throw new Exception("Se requiere un nombre.");
        }
        if(apellido == null || apellido.isEmpty()) {
            throw new Exception("Se requiere un apellido.");
        }
        if(email == null || email.isEmpty()) {
            throw new Exception("Se requiere un e-mail.");
        }
        if(te == null || te.isEmpty()) {
            throw new Exception("Se requiere un teléfono.");
        }
        if(pw1 == null || pw2 == null || pw1.isEmpty() || pw2.isEmpty()) {
            throw new Exception("Las contraseñas no pueden estar vacías.");
        }
        if(!pw1.equals(pw2)) {
            throw new Exception("Las contraseñas no coinciden.");
        }
    }
    
    @Transactional
    public Cliente save(Long dni, String nombre, String apellido, String email, String te, String pw1, String pw2) throws Exception {
        validator(dni, nombre, apellido, email, te, pw1, pw2);
        
        Cliente entity = new Cliente();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        entity.setDni(dni);
        entity.setNombre(nombre);
        entity.setApellido(apellido);
        entity.setEmail(email);
        entity.setTe(te);
        entity.setPassword(encoder.encode(pw1));
        entity.setRol(Role.USER);
        entity.setAlta(true);
        
        return clienteRep.save(entity);
    }
    
    @Transactional
    public Cliente edit(String id, Long dni, String nombre, String apellido, String email, String te, String pw1, String pw2) throws Exception {
        Optional<Cliente> clienteOpt = clienteRep.findById(id);
        Cliente c = clienteOpt.get();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        validatorMod(dni, nombre, apellido, email, te, pw1, pw2);
        
        if(clienteOpt == null) {
            throw new Exception("No existe un usuario con esa ID.");
        }
            c.setDni(dni);
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setEmail(email);
            c.setTe(te);
            c.setPassword(encoder.encode(pw1));
            
            if(c.getRol().equals(Role.ADMIN)){
                c.setRol(Role.ADMIN);
            } else {
                c.setRol(Role.USER);
            }
            
            return clienteRep.save(c);
    }
    
    @Transactional
    public void delete(String id) throws Exception {
        Optional<Cliente> clienteOpt = clienteRep.findById(id);
        
        if(clienteOpt.isPresent()) {
            Cliente c = clienteOpt.get();
            c.setAlta(false);
            clienteRep.save(c);
        } else {
            throw new Exception("No se pudo dar de baja el usuario.");
        }
    }
    
    public void alta(String id) throws Exception{
    Optional<Cliente> clienteOpt = clienteRep.findById(id);
    
    if(clienteOpt.isPresent()){
        Cliente c = clienteOpt.get();
        c.setAlta(true);
        clienteRep.save(c);
    } else {
        throw new Exception("No se pudo dar de alta el usuario.");
    }
}
    
    public void changeRole(String id) throws Exception{
        Optional<Cliente> clienteOpt = clienteRep.findById(id);
        
        if(clienteOpt.isPresent()) {
            Cliente entity = clienteOpt.get();
            
            if(entity.getRol().equals(Role.USER)) {
                entity.setRol(Role.ADMIN);
            } else if (entity.getRol().equals(Role.ADMIN)) {
                entity.setRol(Role.USER);
            } else {
                throw new Exception("No existe un usuario con esa ID.");
            }
        }
    }
    
        @Transactional(readOnly = true)
    public Cliente findOne(String id) {
        return clienteRep.getById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return clienteRep.findAll();
    }
    
    @Transactional(readOnly = true)
    public Cliente findByDni(Long dni) throws Exception{
        if(dni != null) {
            return clienteRep.buscarxDNI(dni);
        } else {
            throw new Exception("No se encontraron clientes con el DNI indicado.");
        }
    }

        @Transactional(readOnly = true)
    public Cliente findByEmail(String email) throws Exception{
        if(email != null) {
            return clienteRep.buscarxEmail(email);
        } else {
            throw new Exception("No se encontraron clientes con el e-mail indicado.");
        }
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Cliente entity = clienteRep.buscarxEmail(email);
       
       if(entity != null) {
         List<GrantedAuthority> permisos = new ArrayList<>();
         
         GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + entity.getRol());
         permisos.add(p1);
         
         ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
         HttpSession session = attr.getRequest().getSession(true);
         session.setAttribute("usersession", entity);
         
         User user = new User(entity.getEmail(), entity.getPassword(), permisos);
         return user;
       } else {
           return null;
       }
    }
}
