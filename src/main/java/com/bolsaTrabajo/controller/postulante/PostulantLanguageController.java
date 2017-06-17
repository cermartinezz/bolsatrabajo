package com.bolsaTrabajo.controller.postulante;


import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.model.catalog.Language;
import com.bolsaTrabajo.model.catalog.LanguageLevel;
import com.bolsaTrabajo.service.LanguageLevelService;
import com.bolsaTrabajo.service.LanguageService;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.service.UserService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/postulante/{username}/idiomas")
public class PostulantLanguageController {

    @Autowired
    private PostulantService postulantService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private LanguageLevelService languageLevelService;

    @Autowired
    private UserService userService;

    @ModelAttribute("usr")
    public User globalUser(Model model) {
        User u = userService.findByUsername(Auth.auth().getName());
        return u;
    }

    @GetMapping("/agregar")
    public String crear(Model model, @PathVariable String username){

        List<Language> languages =languageService.getAllLanguages();
        List<LanguageLevel> languageLevels = languageLevelService.getAllLanguageLevels();
        model.addAttribute("user", Auth.auth());
        model.addAttribute("languages",languages);
        model.addAttribute("languageLevels",languageLevels);
        model.addAttribute("postulantInfo", postulantService.findByUsername(username));

        return "Postulante/idiomas/crear";
    }
}
