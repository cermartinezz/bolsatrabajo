package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.service.InstitutionService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/instituciones")
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;

    @GetMapping
    public String index(Model model){

        model.addAttribute("user", Auth.auth());

        return "instituciones/index_instituciones";
    }

    @GetMapping(value = "/crear")
    public String create(Model model){

        model.addAttribute("user", Auth.auth());

        return "instituciones/create_instituciones";
    }

    @GetMapping(value = "/{code}/editar")
    public String edit(@PathVariable String code, Model model){

        model.addAttribute("user", Auth.auth());

        model.addAttribute("institution",institutionService.findInstitutionByCode(code));

        return "instituciones/edit_instituciones";
    }


}
