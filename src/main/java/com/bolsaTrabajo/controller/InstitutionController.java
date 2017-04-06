package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.Institution;
import com.bolsaTrabajo.util.Auth;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InstitutionController {


    @RequestMapping(value = "/cat/instituciones", method = RequestMethod.GET)
    public String show(Model model){
        model.addAttribute("user", Auth.auth());
        return "instituciones/show_instituciones";
    }

    @RequestMapping(value = "/cat/instituciones/crear", method = RequestMethod.GET)
    public String create(Model model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("institution",new Institution());
        return "instituciones/create_instituciones";
    }


}
