package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.catalog.AcademicTitleCat;
import com.bolsaTrabajo.model.catalog.Publication;
import com.bolsaTrabajo.service.PublicationService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String show(Model model){
        model.addAttribute("user", Auth.auth());
        return "publicaciones/show_publications";
    }

    @RequestMapping(value = "/publicacion/crear")
    public ModelAndView crear(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("publication", new Publication());
        model.addAttribute("user",Auth.auth());
        modelAndView.setViewName("publicaciones/create_publication");
        return modelAndView;
    }

    @RequestMapping(value = "publicacion/{code}/editar")
    public ModelAndView edit(@PathVariable String code, Model model){
        ModelAndView modelAndView = new ModelAndView();
        Publication publication = publicationService.findPublicationByCodigo(code);
        model.addAttribute("user", Auth.auth());
        modelAndView.addObject("publication", publication);
        modelAndView.setViewName("publicaciones/edit_publications");
        return modelAndView;
    }
}
