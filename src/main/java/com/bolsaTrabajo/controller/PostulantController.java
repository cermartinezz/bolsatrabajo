package com.bolsaTrabajo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mvip on 04-04-17.
 */
@Controller
public class PostulantController {

    @RequestMapping("/postulant/profile")
    public String postulantProfile(Model model){
        model.addAttribute("username","Mario Vides");
        return "Postulante/postulant-profile";
    }

}
