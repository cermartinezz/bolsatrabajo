package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.model.catalog.Area;
import com.bolsaTrabajo.service.AreaService;
import com.bolsaTrabajo.service.UserService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AreaController {

    @Autowired
    UserService userService;

    @Autowired
    AreaService areaService;

    @ModelAttribute("usr")
    public User globalUser(Model model) {
        User u = userService.findByUsername(Auth.auth().getName());
        return u;
    }

    @RequestMapping(value = "/examenes/areas", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("areas", areaService.getAll());
        return "admin/exams/areas/index";
    }

    @RequestMapping(value = "/examenes/areas/crear", method = RequestMethod.GET)
    public String crear(Model model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("area", new Area());
        return "admin/exams/areas/crear";
    }

    @RequestMapping(value = "/examenes/areas/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model model){
        Area area = areaService.findById(id);
        model.addAttribute("user", Auth.auth());
        model.addAttribute("area", area);
        return "admin/exams/areas/editar";
    }
}
