package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.util.Auth;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/postulante/{username}/recomendaciones")
public class RecommendationController {

    @GetMapping("/crear")
    public String create(Model model, @PathVariable String username){

        model.addAttribute("user", Auth.auth());

        return "Postulante/recomendaciones/crear";
    }

    @GetMapping("/{recomendationId}/editar")
    public String edit(Model model, @PathVariable String username,@PathVariable Integer recomendationId){

        model.addAttribute("user",Auth.auth());

        model.addAttribute("id",recomendationId);

        return "Postulante/recomendaciones/editar";
    }
}
