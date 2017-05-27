package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.catalog.CompanyCat;
import com.bolsaTrabajo.service.CompanyCatService;
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
 * Created by enan0 on 13/4/2017.
 */
@Controller
@RequestMapping("/empresas")
public class CompanyCatController {

    public static final String CREATE_COMPANY = "empresas/create_company";
    public static final String EDIT_COMPANY = "empresas/edit_company";
    public static final String COMPANY = "company";

    @Autowired
    private CompanyCatService companyCatService;

    @RequestMapping
    public String show(Model model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("companies", companyCatService.getAllCompanies());
        return "empresas/show_company";
    }

    @RequestMapping(value = "/crear", method = RequestMethod.GET)
    public String create(ModelMap modelMap){
        modelMap.addAttribute(COMPANY,new CompanyCat());
        modelMap.addAttribute("user",Auth.auth());
        return CREATE_COMPANY;
    }

    @RequestMapping(value = "/editar/{code}",method = RequestMethod.GET)
    public ModelAndView update(@PathVariable String code){
        ModelAndView modelAndView = new ModelAndView();
        CompanyCat e = companyCatService.getCompany(Long.parseLong(code));
        modelAndView.addObject("company",e);
        modelAndView.setViewName(EDIT_COMPANY);
        return modelAndView;
    }

}
