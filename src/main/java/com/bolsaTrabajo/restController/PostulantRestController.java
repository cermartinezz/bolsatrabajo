package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.CompanyCat;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.model.WorkExperience;
import com.bolsaTrabajo.repositories.PostulantRepository;
import com.bolsaTrabajo.repositories.UserRepository;
import com.bolsaTrabajo.service.CompanyCatService;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by keepercito on 05-01-17.
 */

@RestController
@RequestMapping("/api/postulant")
public class PostulantRestController {

    @Autowired
    PostulantRepository postulantRepository;

    @Autowired
    UserRepository userService;

    @Autowired
    CompanyCatService companyCatService;

    @PostMapping()
    public String storeWorkExp(WorkExperience experience, @RequestParam("empresas") long empresa, RedirectAttributes attributes){

        Postulant p = postulantRepository.findByUsername(Auth.auth().getName());
        CompanyCat c = companyCatService.getCompany(empresa);

        experience.setCompany(c);
        experience.setPostulant(p);

        p.getWorkExperiences().add(experience);
        userService.save(p);
        return "redirect:/postulant/profile";
    }
}
