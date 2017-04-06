package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.Skill;
import com.bolsaTrabajo.model.SkillCategory;
import com.bolsaTrabajo.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mvip on 04-05-17.
 */
@Controller
public class SkillController {

    @Autowired
    private PublicationService publicationService;

    @RequestMapping(value = "/habilidades")
    public String show(){
        return "habilidades/show_skills";
    }

    @RequestMapping(value = "/habilidad/crear")
    public ModelAndView crear(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("skill", new Skill());
        modelAndView.setViewName("habilidades/create_skill");
        return modelAndView;
    }
}
