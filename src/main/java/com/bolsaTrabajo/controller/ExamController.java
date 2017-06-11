package com.bolsaTrabajo.controller;


import com.bolsaTrabajo.model.User;
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

    @ModelAttribute("usr")
    public User globalUser(Model model) {
        User u = userService.findByUsername(Auth.auth().getName());
        return u;
    }

    @RequestMapping(value = "/examenes", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("user", Auth.auth());
        return "admin/exams/index";
    }

    @RequestMapping(value = "/examenes/crear", method = RequestMethod.GET)
    public String crear(Model model){
        model.addAttribute("user", Auth.auth());
        return "admin/exams/index";
    }
}
