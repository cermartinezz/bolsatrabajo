package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by enan0 on 14/4/2017.
 */
@RestController
@RequestMapping("/api/empresas")
public class CompanyRestController {
    public static final Logger logger = LoggerFactory.getLogger(CompanyRestController.class);

    @Autowired
    private CompanyService companyService;

    @RequestMapping(method = RequestMethod.POST)
    public RedirectView store(Company company, RedirectAttributes attributes){

        Company company1 = companyService.getCompany(company.getCompanyName());
        if (company1 != null){
            attributes.addFlashAttribute("message","Empresa "+ company1.getCompanyName()+" ya existe");
            return new RedirectView("/empresas/crear");
        }

        companyService.saveCompany(company);
        attributes.addFlashAttribute("message","Registro se guardo con exito");
        return new RedirectView("/empresas");
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public RedirectView update(Company company, RedirectAttributes attributes){
        Company e = companyService.getCompany(company.getId());
        if (e.getCompanyName().equalsIgnoreCase(company.getCompanyName())){
            logger.info("Ya existe empresa con nombre {}",company.getCompanyName());
            attributes.addFlashAttribute("message","Empresa "+ e.getCompanyName()+" ya existe");
            return new RedirectView("/empresas/crear");
        }
        e.setCompanyName(company.getCompanyName());
        companyService.saveCompany(e);
        attributes.addFlashAttribute("message","Registro modificado con exito");
        return new RedirectView("/empresas");
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public RedirectView delete(Company company, RedirectAttributes attributes){
        companyService.deleteCompany(company);
        attributes.addFlashAttribute("message","Registro se elimino con exito");
        return new RedirectView("/empresas");
    }
}
