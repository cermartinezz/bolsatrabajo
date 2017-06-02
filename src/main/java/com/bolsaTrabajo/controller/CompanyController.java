package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.Company;

import com.bolsaTrabajo.model.Role;
import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.service.CompanyService;
import com.bolsaTrabajo.service.UserService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @Autowired
    UserService userService;

    public Company company;

    @ModelAttribute("usr")
    public User globalUser(Model model) {
        User u = userService.findByUsername(Auth.auth().getName());
        return u;
    }

    @RequestMapping(value= "/company/{user}/profile")
    public String companyProfile(@PathVariable String user, Model model) {
        Company company=companyService.findByUsername(user);
        model.addAttribute("user", Auth.auth().getName());
        model.addAttribute("company",company);
        return "company/profile-company";
    }

 @RequestMapping(value = "/company/{user}/editar")
        public ModelAndView show(@PathVariable String user, Model model){
            ModelAndView modelAndView=new ModelAndView();
            Company company=companyService.findByUsername(user);
            model.addAttribute("user",Auth.auth());
            modelAndView.addObject("company",company);
            modelAndView.setViewName("company/editar");
            return modelAndView;
        }

/*
    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("companys", companyService.getAllCompany());
        return "company/profile-company";

    }

    @RequestMapping(value = "/company/{user}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String user){
        ModelAndView modelAndView = new ModelAndView();
        Company compa = companyService.findByUsername(user);
        modelAndView.addObject("company", compa);
       modelAndView.setViewName("company/editar");
        return modelAndView;
    }*/
   /*@GetMapping("editar")
   public String edit(Model model, @PathVariable String username){

       model.addAttribute("user", Auth.auth());

       this.company= companyService.findByUsername(username);

       model.addAttribute("company", this.company);

       return "company/editar";
   }*/

}
