package com.bolsaTrabajo.controller;


import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.model.Job;
import com.bolsaTrabajo.service.*;
import com.bolsaTrabajo.util.Auth;
import com.bolsaTrabajo.validator.CompanyValidator;
import com.bolsaTrabajo.validator.JobValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JobController {

    public static final Logger log = LoggerFactory.getLogger(RegistrarController.class);
    @Autowired
    private CompanyValidator companyValidator;
    public Job job;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    @Autowired
    private JobService jobService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private JobValidator jobValidator;

    @Autowired
    private RoleService roleRepository;


    @RequestMapping(value = "/puesto/crear", method = RequestMethod.GET)
    public String registrationC(Model model) {
        boolean logeado = Auth.check();
        if (!logeado) {
            return "redirect:/";
        } else {
          //  Company company=companyService.findByUsername(Auth.auth().getName());
            model.addAttribute("userForm", new Job());
            model.addAttribute("user", Auth.auth());
            return "job/create_job";
        }
    }
    @RequestMapping(value="/puesto/lista",method = RequestMethod.GET)
    public String show( Model model){

        model.addAttribute("user", Auth.auth());
        Company company=companyService.findByUsername(Auth.auth().getName());
        model.addAttribute("empresa", company);
        return "job/lista";
    }
    @RequestMapping(value="/puesto/{id}/perfil",method = RequestMethod.GET)
    public String perfil( @PathVariable Long id,Model model){

        Job job=jobService.findById(id);
        model.addAttribute("user", Auth.auth());
        Company company=companyService.findByUsername(Auth.auth().getName());
        model.addAttribute("empresa", company);
        model.addAttribute("job",job);
        return "job/profile";
    }
    @RequestMapping(value="/puesto/{id}/editar",method = RequestMethod.GET)
    public String editar( @PathVariable Long id,Model model){

        Job job=jobService.findById(id);
        model.addAttribute("user", Auth.auth());
        Company company=companyService.findByUsername(Auth.auth().getName());
        model.addAttribute("empresa", company);
        model.addAttribute("job",job);
        return "job/edit";
    }


    }


