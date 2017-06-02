package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.catalog.AcademicTitleCat;
import com.bolsaTrabajo.service.AcademicTitleCatService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by keepercito on 05-21-17.
 */

@Controller
@RequestMapping("/cat/titulos")
public class AcademicTitleCatController {

    public static final String CREATE_TITLE = "titulos/create_title";
    public static final String EDIT_TITLE = "titulos/edit_title";
    public static final String TITLE = "title";

    @Autowired
    private AcademicTitleCatService academicTitleCatService;

    @RequestMapping
    public String show(Model model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("titles", academicTitleCatService.getAllTitles());
        return "titulos/show_title";
    }

    @RequestMapping(value = "/crear", method = RequestMethod.GET)
    public String create(ModelMap modelMap){
        modelMap.addAttribute(TITLE,new AcademicTitleCat());
        modelMap.addAttribute("user",Auth.auth());
        return CREATE_TITLE;
    }

    @RequestMapping(value = "/editar/{code}",method = RequestMethod.GET)
    public ModelAndView update(@PathVariable String code){
        ModelAndView modelAndView = new ModelAndView();
        AcademicTitleCat t = academicTitleCatService.getTitle(Long.parseLong(code)).get();
        modelAndView.addObject("user",Auth.auth());
        modelAndView.addObject("title",t);
        modelAndView.setViewName(EDIT_TITLE);
        return modelAndView;
    }
}
