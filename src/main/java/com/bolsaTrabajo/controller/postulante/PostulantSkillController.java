package com.bolsaTrabajo.controller.postulante;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.model.catalog.SkillCategory;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.service.SkillCategoryService;
import com.bolsaTrabajo.service.UserService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/postulante/{username}/habilidades")
public class PostulantSkillController {

    @Autowired
    private PostulantService postulantService;

    @Autowired
    private SkillCategoryService skillCategoryService;

    @Autowired
    private UserService userService;

    @ModelAttribute("usr")
    public User globalUser(Model model) {
        User u = userService.findByUsername(Auth.auth().getName());
        return u;
    }

    @GetMapping("/crear")
    public String crear(Model model, @PathVariable String username){

        List<SkillCategory> category = new ArrayList<>();

        List<SkillCategory> categories = skillCategoryService.getAllSkillsCategory();

        model.addAttribute("category",category);

        model.addAttribute("categories", categories);

        model.addAttribute("user", Auth.auth());

        model.addAttribute("postulantInfo", postulantService.findByUsername(username));

        return "Postulante/habilidades/crear";

    }

    @GetMapping("/{skillId}/editar")
    public String editar(Model model,
                         @PathVariable String username,
                         @PathVariable Integer skillId){

        Postulant postulant = postulantService.findByUsername(username);

        List<SkillCategory> category = new ArrayList<>();

        List<SkillCategory> categories = skillCategoryService.getAllSkillsCategory();

        model.addAttribute("category",category);

        model.addAttribute("categories", categories);

        model.addAttribute("user", Auth.auth());

        model.addAttribute("postulantInfo", postulant);

        model.addAttribute("id",skillId);

        return "Postulante/habilidades/editar";

    }


}
