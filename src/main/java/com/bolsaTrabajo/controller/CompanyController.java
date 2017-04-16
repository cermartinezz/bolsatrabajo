package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.service.CompanyService;
import com.bolsaTrabajo.util.Auth;
import org.slf4j.LoggerFactory;
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
public class CompanyController {

    public static final String CREATE_COMPANY = "empresas/create_company";
    public static final String EDIT_COMPANY = "empresas/edit_company";
    public static final String COMPANY = "company";
    //public static final Logger logger = LoggerFactory.getLogger(CompanyServices.Class);

    @Autowired
    private CompanyService companyService;

    @RequestMapping
    public String show(Model model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("companies",companyService.getAllCompanies());
        return "empresas/show_company";
    }

    @RequestMapping(value = "/crear", method = RequestMethod.GET)
    public String create(ModelMap modelMap){
        modelMap.addAttribute(COMPANY,new Company());
        modelMap.addAttribute("user",Auth.auth());
        return CREATE_COMPANY;
    }

    @RequestMapping(value = "/editar/{code}",method = RequestMethod.GET)
    public ModelAndView update(@PathVariable String code){
        ModelAndView modelAndView = new ModelAndView();
        Company e = companyService.getCompany(Long.parseLong(code));
        modelAndView.addObject("company",e);
        modelAndView.setViewName(EDIT_COMPANY);
        return modelAndView;
    }

}
