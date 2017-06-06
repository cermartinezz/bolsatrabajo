package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.service.CompanyService;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.service.UserService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PostulantService postulantService;

    @Autowired
    CompanyService companyService;

    @ModelAttribute("usr")
    public User globalUser(Model model) {
        User u = userService.findByUsername(Auth.auth().getName());
        return u;
    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("usuarios", userService.getAll());
        return "admin/users/index";
    }

    @RequestMapping(value = "/usuarios/{username}", method = RequestMethod.GET)
    public RedirectView show(@PathVariable String username, Model model, RedirectAttributes attributes){
        Postulant postulant = postulantService.findByUsername(username);
        if (postulant==null){
            Company company = companyService.findByUsername(username);
            if (company==null){
                attributes.addFlashAttribute("message","No se pudo determinar el tipo de Usuario");
                return new RedirectView("/usuarios");
            }
            model.addAttribute("user", Auth.auth());
            return new RedirectView("/company/"+username+"/editar");
        }

        model.addAttribute("user", Auth.auth());
        return new RedirectView("/postulante/"+username+"/editar");
    }

}
