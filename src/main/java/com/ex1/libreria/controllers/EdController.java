package com.ex1.libreria.controllers;

import com.ex1.libreria.entities.Editorial;
import com.ex1.libreria.services.EditorialServ;
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
@RequestMapping("/editorial")
public class EdController {

@Autowired
private EditorialServ editServ;

    @GetMapping("/list-edit")
    public String lista(ModelMap modelo) {

        List<Editorial> editorialesLista = editServ.findAll();

        modelo.addAttribute("editoriales", editorialesLista);

        return "list-edit";
    }

@GetMapping("/form-edit")
public String formularioEd() {
    return "form-edit.html";
}

@PostMapping("/form-edit")
public String guardar(ModelMap model, @RequestParam String nombre) throws Exception {
    
    try{
        editServ.save(nombre);
        model.put("exito", "La editorial ha sido registrada exitosamente");
        return "form-edit.html";
    } catch (Exception e) {
//        e.printStackTrace();
        model.put("error", e.getMessage());
        return "form-edit.html";
    }
}

    @GetMapping("/baja/{id}")
    public String baja(@PathVariable String id) {

        try {
            editServ.delete(id);
            return "redirect:/editorial/list-edit";
        } catch (Exception e) {
            return "redirect:/";
        }
    }
    
        @GetMapping("/alta/{id}")
    public String alta(@PathVariable String id) {

        try {
            editServ.alta(id);
            return "redirect:/editorial/list-edit";
        } catch (Exception e) {
            return "redirect:/";
        }
    }
    
    @GetMapping("/modify/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) throws Exception {

        modelo.put("editorial", editServ.findOne(id));

        return "form-edit-modif";
    }

    @PostMapping("/modify/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam String nombre) throws Exception {

        try {
            editServ.edit(id, nombre);
            return "redirect:/editorial/list-edit";
            
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            modelo.put("editorial", editServ.findOne(id));
            return "form-edit-modif";
        }
    }

}
