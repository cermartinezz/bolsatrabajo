package com.bolsaTrabajo.controller.JobProfile;

import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.service.CompanyService;
import com.bolsaTrabajo.service.JobProfileService;
import com.bolsaTrabajo.service.JobService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empresa/{username}/perfiles")
public class JobProfileController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private JobService jobService;

    @Autowired
    private JobProfileService jobProfileService;

    private Company company;

    /*
        Sirve para pasarle a todas las vista la misma variable
     */
    @ModelAttribute("company")
    public Company globalCompany(Model model) {
        this.company = companyService.findByUsername(Auth.auth().getName());
        return this.company;
    }

    @ModelAttribute("user")
    public Authentication globalAuth(Model model) {
        return Auth.auth();
    }

    @GetMapping
    public String index(@PathVariable String  username, Model model){
        boolean logeado = Auth.check();
        if (!logeado) {
            return "redirect:/";
        } else {
            return "puestos/perfil/index";
        }
    }


    @GetMapping(value="/crear")
    public String crear(@PathVariable String  username, Model model){

        boolean logeado = Auth.check();
        if (!logeado) {
            return "redirect:/";
        } else {
            return "puestos/perfil/crear";
        }
    }

    @GetMapping(value="/codigo/{id}")
    public String show(@PathVariable String  username,@PathVariable Integer id, Model model){
        boolean logeado = Auth.check();
        if (!logeado) {
            return "redirect:/";
        } else {
            model.addAttribute("profile", jobProfileService.findById(id));
            return "puestos/perfil/show";
        }

    }


}
