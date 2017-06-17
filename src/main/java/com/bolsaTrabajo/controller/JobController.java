package com.bolsaTrabajo.controller;


import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.model.Job;
import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.model.catalog.Department;
import com.bolsaTrabajo.model.jobInfo.Candidate;
import com.bolsaTrabajo.model.jobInfo.JobProfile;
import com.bolsaTrabajo.service.*;
import com.bolsaTrabajo.util.Auth;
import com.bolsaTrabajo.validator.CompanyValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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


    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CandidateService candidateService;

    @ModelAttribute("usr")
    public User globalUser(Model model) {
        User u = userService.findByUsername(Auth.auth().getName());
        return u;
    }

    @RequestMapping(value = "/puesto/crear", method = RequestMethod.GET)
    public String registrationC(Model model) {

        List<Department> departments_list = departmentService.getAllDepartments();

        boolean logeado = Auth.check();
        if (!logeado) {
            return "redirect:/";
        } else {
            Company company = companyService.findByUsername(Auth.auth().getName());
            List<JobProfile> profiles = jobProfileService.findAllByCompany(company);
            List<JobProfile> profile = new ArrayList<>();
            model.addAttribute("jobProfiles", profiles);
            model.addAttribute("jobProfile", profile);
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
        Set<Candidate> candidates = job.getCandidates();
        //List<Candidate> postulantsForJob = candidateService.getPostulantForJob(job);
        model.addAttribute("candidates",candidates);
        return "job/lista_aspirantes";
    }

}


