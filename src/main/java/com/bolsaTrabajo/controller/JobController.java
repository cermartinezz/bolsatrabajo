package com.bolsaTrabajo.controller;


import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.model.Job;
import com.bolsaTrabajo.model.jobInfo.JobProfile;
import com.bolsaTrabajo.service.CompanyService;
import com.bolsaTrabajo.service.JobProfileService;
import com.bolsaTrabajo.service.JobService;
import com.bolsaTrabajo.service.UserService;
import com.bolsaTrabajo.util.Auth;
import com.bolsaTrabajo.validator.CompanyValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

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
    private JobProfileService jobProfileService;



    @RequestMapping(value = "/puesto/crear", method = RequestMethod.GET)
    public String registrationC(Model model) {
        boolean logeado = Auth.check();
        if (!logeado) {
            return "redirect:/";
        } else {
            Company company = companyService.findByUsername(Auth.auth().getName());
            List<JobProfile> profiles = jobProfileService.findAllByCompany(company);
            model.addAttribute("profiles", profiles);
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


