package com.ex1.libreria.controllers;

import com.ex1.libreria.entities.Autor;
import com.ex1.libreria.entities.Editorial;
import com.ex1.libreria.entities.Libro;
import com.ex1.libreria.services.AutorServ;
import com.ex1.libreria.services.EditorialServ;
import com.ex1.libreria.services.LibroServ;
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
@RequestMapping("/book")//localhost:8080/libro
public class LibroController {

@Autowired
private LibroServ libroServ;

@Autowired
private AutorServ autorServ;

@Autowired
private EditorialServ editServ;

    @GetMapping("/list-book")
    public String lista(ModelMap modelo) {

        List<Libro> librosLista = libroServ.findAll();

        modelo.addAttribute("libros", librosLista);

        return "list-book";
    }

@GetMapping("/form-book")
    public String formulario(ModelMap modelo) {
        List<Autor> L = autorServ.findAll();
        modelo.put("autores", L);
        
        List<Editorial> E = editServ.findAll();
        modelo.put("editoriales", E);
        
        return "form-book";
    }

@PostMapping("/form-book")
public String guardar(ModelMap model, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer cant, @RequestParam Integer prestados, @RequestParam Integer restantes, @RequestParam Autor a, @RequestParam Editorial ed) throws Exception {
    
    try{
        libroServ.save(titulo, anio, cant, prestados, restantes, a, ed);
        model.put("exito", "El libro ha sido registrado exitosamente");
        return "form-book.html";
    } catch (Exception e) {
//        e.printStackTrace();
        model.put("error", e.getMessage());
        return "form-book.html";
    }
}

    @GetMapping("/baja/{id}")
    public String baja(@PathVariable String id) {

        try {
            libroServ.delete(id);
            return "redirect:/book/list-book";
        } catch (Exception e) {
            return "redirect:/";
        }
    }
    
        @GetMapping("/alta/{id}")
    public String alta(@PathVariable String id) {

        try {
            libroServ.alta(id);
            return "redirect:/book/list-book";
        } catch (Exception e) {
            return "redirect:/";
        }
    }
    
    @GetMapping("/modify/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) throws Exception {

        modelo.put("libro", libroServ.findOne(id));

        List<Autor> L = autorServ.findAll();
        modelo.put("autores", L);
        
        List<Editorial> E = editServ.findAll();
        modelo.put("editoriales", E);
        
        return "form-book-modif";
    }
    
    @PostMapping("/modify/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer cant, @RequestParam Integer prestados, @RequestParam Integer restantes, @RequestParam Autor a, @RequestParam Editorial ed) throws Exception {

        try {
            libroServ.edit(id, titulo, anio, cant, prestados, restantes, a, ed);
            return "redirect:/book/list-book";
            
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            modelo.put("libro", libroServ.findOne(id));
            return "form-book-modif";
        }
    }

}
