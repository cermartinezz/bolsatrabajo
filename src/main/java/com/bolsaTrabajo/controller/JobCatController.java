package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.catalog.JobCat;
import com.bolsaTrabajo.service.JobCatService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by keepercito on 05-01-17.
 */

@Controller
@RequestMapping("/jobs")
public class JobCatController {

    public static final String CREATE_JOB = "puestos/create_job";
    public static final String EDIT_JOB = "puestos/edit_job";
    public static final String JOB = "job";

    @Autowired
    private JobCatService jobCatService;

    @RequestMapping
    public String show(Model model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("jobs", jobCatService.getAllJobs());
        return "puestos/show_job";
    }

    @RequestMapping(value = "/crear", method = RequestMethod.GET)
    public String create(ModelMap modelMap){
        modelMap.addAttribute(JOB,new JobCat());
        modelMap.addAttribute("user",Auth.auth());
        return CREATE_JOB;
    }

    @RequestMapping(value = "/editar/{code}",method = RequestMethod.GET)
    public ModelAndView update(@PathVariable String code){
        ModelAndView modelAndView = new ModelAndView();
        JobCat e = jobCatService.getJob(Long.parseLong(code));
        modelAndView.addObject("job",e);
        modelAndView.setViewName(EDIT_JOB);
        return modelAndView;
    }
}
