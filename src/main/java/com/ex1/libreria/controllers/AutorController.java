package com.ex1.libreria.controllers;

import com.ex1.libreria.entities.Autor;
import com.ex1.libreria.services.AutorServ;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/author")
public class AutorController {

@Autowired
private AutorServ autorServ;


    @GetMapping("/list-author")
    public String lista(ModelMap modelo) {

        List<Autor> autoresLista = autorServ.findAll();

        modelo.addAttribute("autores", autoresLista);

        return "list-author";
    }

@GetMapping("/form-author")
public String formularioA() {
    return "form-author.html";
}

@PostMapping("/form-author")
public String guardar(ModelMap model, @RequestParam String nombre) throws Exception {
    
    try{
        autorServ.save(nombre);
        model.put("exito", "Autor/a registrado/a exitosamente");
        return "form-author.html";
    } catch (Exception e) {
//        e.printStackTrace();
        model.put("error", e.getMessage());
        return "form-author.html";
    }
}

    @GetMapping("/baja/{id}")
    public String baja(@PathVariable String id) {

        try {
            autorServ.delete(id);
            return "redirect:/author/list-author";
        } catch (Exception e) {
            return "redirect:/";
        }
    }
    
        @GetMapping("/alta/{id}")
    public String alta(@PathVariable String id) {

        try {
            autorServ.alta(id);
            return "redirect:/author/list-author";
        } catch (Exception e) {
            return "redirect:/";
        }
    }
    
    @GetMapping("/modify/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {

        modelo.put("autor", autorServ.getOne(id));

        return "form-author-modif";
    }

    @PostMapping("/modify/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam String nombre) {

        try {
            autorServ.edit(id, nombre);
            return "redirect:/author/list-author";
            
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            modelo.put("autor", autorServ.getOne(id));
            return "form-author-modif";
        }
    }
}
