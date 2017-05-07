package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.WorkExperience;
import com.bolsaTrabajo.service.CompanyCatService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mvip on 04-04-17.
 */
@Controller
public class PostulantController {

    @Autowired
    CompanyCatService companyCatService;

    @RequestMapping("/postulant/profile")
    public String postulantProfile(Model model){
        model.addAttribute("username", Auth.auth().getName());
        return "Postulante/postulant-profile";
    }

    @RequestMapping("/postulant/workExp")
    public String workExperience(Model model){
        model.addAttribute("companies",companyCatService.getAllCompanies());
        model.addAttribute("workExp", new WorkExperience());
        return "exp_labo/create_workExp";
    }

}
