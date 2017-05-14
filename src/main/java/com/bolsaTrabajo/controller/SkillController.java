package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.Skill;
import com.bolsaTrabajo.model.SkillCategory;
import com.bolsaTrabajo.service.PublicationService;
import com.bolsaTrabajo.service.SkillService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SkillController {

    @Autowired
    private SkillService skillService;

    @RequestMapping(value = "/habilidades")
    public String show(Model model){
        model.addAttribute("user", Auth.auth());
        return "habilidades/show_skills";
    }

    @RequestMapping(value = "/habilidad/crear")
    public ModelAndView crear(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("skill", new Skill());
        model.addAttribute("user",Auth.auth());
        modelAndView.setViewName("habilidades/create_skill");
        return modelAndView;
    }

    @RequestMapping(value = "habilidad/{code}/editar")
    public ModelAndView edit(@PathVariable String code, Model model){
        ModelAndView modelAndView = new ModelAndView();
        Skill skill = skillService.findSkillByCodigo(code);
        model.addAttribute("user",Auth.auth());
        modelAndView.addObject("skill",skill);
        modelAndView.setViewName("habilidades/edit_skill");
        return modelAndView;

    }
}
