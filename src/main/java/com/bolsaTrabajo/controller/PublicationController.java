package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.Publication;
import com.bolsaTrabajo.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mvip on 04-04-17.
 */
@Controller
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @RequestMapping(value = "/publicaciones")
    public String show(){
        return "publicaciones/show_publications";
    }

    @RequestMapping(value = "/publicacion/crear")
    public ModelAndView crear(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("publication", new Publication());
        modelAndView.setViewName("publicaciones/create_publication");
        return modelAndView;
    }

    @RequestMapping(value = "publicacion/{code}/editar")
    public ModelAndView edit(@PathVariable String code){
        ModelAndView modelAndView = new ModelAndView();
        Publication publication = publicationService.findPublicationByCodigo(code);
        modelAndView.addObject("publication", publication);
        modelAndView.setViewName("publicaciones/edit_publications");
        return modelAndView;
    }
}
