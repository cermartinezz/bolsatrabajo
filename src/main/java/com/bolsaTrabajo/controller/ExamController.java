package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.Question;
import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.model.catalog.SubArea;
import com.bolsaTrabajo.service.ExamService;
import com.bolsaTrabajo.service.QuestionService;
import com.bolsaTrabajo.service.SubAreaService;
import com.bolsaTrabajo.service.UserService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExamController {
    @Autowired
    UserService userService;

    @Autowired
    SubAreaService subAreaService;

    @Autowired
    ExamService examService;

    @Autowired
    QuestionService questionService;

    @ModelAttribute("usr")
    public User globalUser(Model model) {
        User u = userService.findByUsername(Auth.auth().getName());
        return u;
    }

    @RequestMapping(value = "/examenes", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("examenes",examService.getAll());
        return "admin/exams/index";
    }

    @RequestMapping(value = "/examenes/crear", method = RequestMethod.GET)
    public String crear(Model model){
        long id = 623;
        int peso = 1;
        SubArea subArea = subAreaService.findById(id);
        Question question = new Question();

        return "admin/exams/index";
    }
}
