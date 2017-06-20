package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.Question;
import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.model.catalog.SubArea;
import com.bolsaTrabajo.service.QuestionService;
import com.bolsaTrabajo.service.SubAreaService;
import com.bolsaTrabajo.service.UserService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    UserService userService;

    @Autowired
    SubAreaService subAreaService;

    @Autowired
    QuestionService questionService;

    @ModelAttribute("usr")
    public User globalUser(Model model) {
        User u = userService.findByUsername(Auth.auth().getName());
        return u;
    }

    @RequestMapping(value = "/examenes/preguntas", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("questions",questionService.getAll());
        return "admin/exams/questions/index";
    }

    @RequestMapping(value = "/examenes/preguntas/crear", method = RequestMethod.GET)
    public String crear(Model model){
        List<SubArea> subAreas = new ArrayList<>(subAreaService.getAll());
        model.addAttribute("user", Auth.auth());
        model.addAttribute("subAreas", subAreas);
        model.addAttribute("question", new Question());
        return "admin/exams/questions/crear";
    }

    @RequestMapping(value = "/examenes/preguntas/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model model){
        List<SubArea> subAreas = new ArrayList<>(subAreaService.getAll());
        Question question = questionService.findById(id);
        model.addAttribute("user", Auth.auth());
        model.addAttribute("subareas",subAreas);
        model.addAttribute("question", question);
        return "admin/exams/questions/editar";
    }
}
