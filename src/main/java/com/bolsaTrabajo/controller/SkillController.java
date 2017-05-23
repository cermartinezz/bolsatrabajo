package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.Skill;
import com.bolsaTrabajo.model.SkillCategory;
import com.bolsaTrabajo.service.PublicationService;
import com.bolsaTrabajo.service.SkillCategoryService;
import com.bolsaTrabajo.service.SkillService;
import com.bolsaTrabajo.util.Auth;
import com.bolsaTrabajo.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SkillController {

    @Autowired
    private SkillService skillService;

    @Autowired
    private SkillCategoryService skillCategoryService;

    @RequestMapping(value = "/habilidades")
    public String index(Model model){
        model.addAttribute("user", Auth.auth());
        return "habilidades/show_skills";
    }

    @GetMapping(value = "{code}")
    public String show(Model model,@PathVariable String code){
        model.addAttribute("user",Auth.auth());
        model.addAttribute("skill",skillService.findSkillByCodigo(code));
        return "";
    }

    @RequestMapping(value = "/habilidad/crear")
    public String crear(Model model){
        List<SkillCategory> skillCategory = new ArrayList<>();
        List<SkillCategory> categories = skillCategoryService.getAllSkillsCategory();


        model.addAttribute("skill", new Skill());
        model.addAttribute("user",Auth.auth());
        model.addAttribute("skillCategory", skillCategory);
        model.addAttribute("categories",categories);

        return "habilidades/create_skill";
    }

    @RequestMapping(value = "habilidad/{code}/editar", method = RequestMethod.GET)
    public String edit(@PathVariable String code, Model model){
        Skill skill = skillService.findSkillByCodigo(code);
        List<SkillCategory> skillCategory = new ArrayList<>();
        List<SkillCategory> categories = skillCategoryService.getAllSkillsCategory();

        model.addAttribute("user",Auth.auth());
        model.addAttribute("skill",skill);
        model.addAttribute("skillCategory", skillCategory);
        model.addAttribute("categories",categories);

        return "habilidades/edit_skill";

    }

    @RequestMapping(value = "habilidad/{code}/eliminar")
    public ResponseEntity<?> eliminar(@PathVariable("code") String code){
        Skill skill = skillService.findSkillByCodigo(code);
        if (skill == null){
            return new ResponseEntity(new CustomErrorType("No existe habilidad con ese codigo"+code), HttpStatus.NOT_FOUND);
        }
        skillService.deleteSkill(code);
        return new ResponseEntity<Skill>(HttpStatus.NO_CONTENT);
    }
}
