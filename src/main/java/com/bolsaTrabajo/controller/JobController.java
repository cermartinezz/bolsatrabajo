package com.bolsaTrabajo.controller;


import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.model.Job;
import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.model.catalog.Department;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.OverridesAttribute;
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
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private JobValidator jobValidator;

    @Autowired
    private RoleService roleRepository;

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/puesto/crear", method = RequestMethod.GET)
    public String registrationC(Model model) {

        List<Department> departments_list = departmentService.getAllDepartments();

        boolean logeado = Auth.check();
        if (!logeado) {
            return "redirect:/";
        } else {
          //  Company company=companyService.findByUsername(Auth.auth().getName());
            model.addAttribute("userForm", new Job());
            model.addAttribute("user", Auth.auth());
            model.addAttribute("departments_list",departments_list);
            return "job/create_job";
        }
    }

    @RequestMapping(value="/puesto/lista",method = RequestMethod.GET)
    public String showAll( Model model){
        model.addAttribute("user", Auth.auth());
        Company company=companyService.findByUsername(Auth.auth().getName());
        List<Job> jobs = jobService.findAllByCompany(company);
        model.addAttribute("empresa", company);
        model.addAttribute("jobs",jobs);
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

    @RequestMapping(value="/puesto/{id}/ver",method = RequestMethod.GET)
    public String show( @PathVariable Long id,Model model){
        Job job=jobService.findById(id);
        model.addAttribute("user", Auth.auth());
        Company company=companyService.findByUsername(Auth.auth().getName());
        User userPostulant = userService.findByUsername(Auth.auth().getName());
        model.addAttribute("userPostulant",userPostulant);
        model.addAttribute("empresa", company);
        model.addAttribute("id",id);
        model.addAttribute("job",job);
        return "job/show_job";
    }

    @RequestMapping(value = "/puesto/{id}/aspirantes",method = RequestMethod.GET)
    public String showAspirants(@PathVariable Long id, Model model){
        model.addAttribute("user", Auth.auth());
        Job job = jobService.findById(id);
        job.getCandidates();
        model.addAttribute("job_candidates",job);
        return "job/lista_aspirantes";
    }

}


