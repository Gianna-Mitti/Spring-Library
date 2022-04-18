package com.ex1.libreria.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping(value="/error", method={RequestMethod.GET, RequestMethod.POST})
public class ErrorsController implements ErrorController{
 
@RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})    
 public String pagError(HttpServletRequest httpServletRequest, ModelMap model) {
    String errorMsg="";
//    int httpErrorCode = getErrorCode(httpRequest);
    
  int code = (int) httpServletRequest.getAttribute("javax.servlet.error.status_code");    
    switch(code) {
            case 400:
                errorMsg = "El recurso solicitado no existe.";
                break;
            case 401:
                errorMsg= "No tiene autorización para acceder a este recurso.";
                break;
            case 403:
                errorMsg= "No tiene permisos para acceder a este recurso.";
                break;
            case 404:
                errorMsg= "No se encuentra el recurso solicitado.";
                break;
            case 500:
                errorMsg= "El servidor no pudo ejecutar la peticion con éxito.";
                break;
        }       
   
            model.put("error", errorMsg);
        model.put("codigo", code);
        return "error";
}
     
    public String getErrorPath() {
        return "/error";
    }
}
