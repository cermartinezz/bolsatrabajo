package com.bolsaTrabajo.controller.JobProfile;

import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.service.CompanyService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/puesto/{job}/perfil")
public class JobProfileController {

    @Autowired
    CompanyService companyService;

    /*
        Sirve para pasarle a todas las vista la misma variable
     */
    @ModelAttribute("usr")
    public User globalUser(Model model) {
        User u = companyService.findByUsername(Auth.auth().getName());
        return u;
    }

    @GetMapping
    public String crear(Model model){
        model.addAttribute("auth", Auth.auth());

        return "puestos/perfil/crear";
    }

}
