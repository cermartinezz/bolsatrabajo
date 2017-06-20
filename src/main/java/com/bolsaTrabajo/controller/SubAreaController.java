package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.model.catalog.Area;
import com.bolsaTrabajo.model.catalog.SubArea;
import com.bolsaTrabajo.service.AreaService;
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
public class SubAreaController {

    @Autowired
    UserService userService;

    @Autowired
    AreaService areaService;

    @Autowired
    SubAreaService subAreaService;

    @ModelAttribute("usr")
    public User globalUser(Model model) {
        User u = userService.findByUsername(Auth.auth().getName());
        return u;
    }

    @RequestMapping(value = "/examenes/subareas", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("subareas", subAreaService.getAll());
        return "admin/exams/subareas/index";
    }

    @RequestMapping(value = "/examenes/subareas/crear", method = RequestMethod.GET)
    public String crear(Model model){
        List<Area> areas = new ArrayList<>(areaService.getAll());
        model.addAttribute("user", Auth.auth());
        model.addAttribute("areas",areas);
        model.addAttribute("subArea", new SubArea());
        return "admin/exams/subareas/crear";
    }

    @RequestMapping(value = "/examenes/subareas/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model model){
        SubArea subArea = subAreaService.findById(id);
        List<Area> areas = new ArrayList<>(areaService.getAll());
        model.addAttribute("user", Auth.auth());
        model.addAttribute("areas",areas);
        model.addAttribute("subarea", subArea);
        return "admin/exams/subareas/editar";
    }
}
