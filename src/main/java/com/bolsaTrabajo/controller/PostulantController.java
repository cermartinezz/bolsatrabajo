package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.Certification;
import com.bolsaTrabajo.model.Institution;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.service.CertificationService;
import com.bolsaTrabajo.service.InstitutionService;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/postulante/{username}")
public class PostulantController {

    @Autowired
    private PostulantService postulantService;

    @Autowired
    private InstitutionService institutionService;


    public Postulant postulant;


    @GetMapping("perfil")
    public String profile(Model model, @PathVariable String username){

        if( !username.equals("anonymousUser") ){
            model.addAttribute("user", Auth.auth());

            this.postulant = postulantService.findByUsername(username);

            model.addAttribute("postulant", this.postulant);

            return "Postulante/profile";
        }
        return "redirect:/";
    }

    @GetMapping("editar")
    public String edit(Model model, @PathVariable String username){

        model.addAttribute("user", Auth.auth());

        this.postulant = postulantService.findByUsername(username);

        model.addAttribute("postulant", this.postulant);

        return "Postulante/editar";
    }

    @GetMapping("/certificaciones/agregar")
    public String certificaciones(Model model, @PathVariable String username){

        List<Institution> institution = new ArrayList<>();

        List<Institution> institutions = institutionService.getAllInstitutions();

        model.addAttribute("institution", institution);

        model.addAttribute("institutions", institutions);


        model.addAttribute("user", Auth.auth());

        model.addAttribute("postulant", postulantService.findByUsername(username));

        return "Postulante/certificaciones/crear";
    }

    /*@RequestMapping("/postulant/workExp")
    public String workExperience(Model model){
        model.addAttribute("companies",companyCatService.getAllCompanies());
        model.addAttribute("workExp", new WorkExperience());
        return "exp_labo/create_workExp";
    }*/

    @GetMapping("/publicaciones/agregar")
    public String publicaciones(Model model, @PathVariable String username){

        model.addAttribute("user", Auth.auth());

        model.addAttribute("postulant", postulantService.findByUsername(username));

        return "Postulante/certificaciones/crear";
    }
}
