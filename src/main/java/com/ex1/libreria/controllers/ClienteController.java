package com.ex1.libreria.controllers;

import com.ex1.libreria.entities.Cliente;
import com.ex1.libreria.services.ClienteServ;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/client")
public class ClienteController {

@Autowired
private ClienteServ clienteServ;

@GetMapping("/form-client")
public String formulario() {
    return "form-client";
}

@PostMapping("/form-client")
public String guardar(ModelMap model, @RequestParam Long dni, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String te, @RequestParam String pw1, @RequestParam String pw2) throws Exception {
    
    try{
        clienteServ.save(dni, nombre, apellido, email, te, pw1, pw2);
        model.put("exito", "El usuario ha sido registrado exitosamente. \n Ya puede ingresar.");
        return "form-client";
    } catch (Exception e) {
        model.put("error", e.getMessage());
        return "form-client";
    }
}

@GetMapping("/list")
public String lista(ModelMap model) {
    List<Cliente> listaCliente = clienteServ.findAll();
    
    model.addAttribute("clientes", listaCliente);
    
    return "list-client";
}

@GetMapping("/baja/{id}")
    public String baja(@PathVariable String id) {

        try {
            clienteServ.delete(id);
            return "redirect:/client/list";
        } catch (Exception e) {
            return "redirect:/";
        }
    }
    
        @GetMapping("/alta/{id}")
    public String alta(@PathVariable String id) {

        try {
            if(clienteServ.findOne(id) != null) {
            clienteServ.alta(id);    
            }
            return "redirect:/client/list";
        } catch (Exception e) {
            return "redirect:/";
        }
    }  
    
        @GetMapping("/modify/{id}")
    public String editar(ModelMap model, HttpSession session, @PathVariable String id) {
        try {
            Cliente c = (Cliente) session.getAttribute("usersession");
            model.put("cliente", c);
        } catch (Exception e) {
        }
        return "form-client-modif";
    }

        @PostMapping("/modify/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam Long dni, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String te, @RequestParam String pw1, @RequestParam String pw2, HttpSession session) {

        if(clienteServ.findOne(id) != null){
                    try {
            Cliente c = clienteServ.edit(id, dni, nombre, apellido, email, te, pw1, pw2);
            session.setAttribute("usersession", c);
            return "redirect:/client/list";
            
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            modelo.put("cliente", clienteServ.findOne(id));
            return "form-client-modif";
        }
        }
    return null;
    }
}

