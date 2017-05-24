package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.CompanyCat;
import com.bolsaTrabajo.service.CompanyCatService;
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
public class CompanyCatRestController {

    @Autowired
    private CompanyCatService companyCatService;

    @RequestMapping(method = RequestMethod.POST)
    public RedirectView store(CompanyCat companyCat, RedirectAttributes attributes){

        CompanyCat companyCat1 = companyCatService.getCompany(companyCat.getCompanyName());
        if (companyCat1 != null){
            attributes.addFlashAttribute("message","Empresa "+ companyCat1.getCompanyName()+" ya existe");
            return new RedirectView("/empresas/crear");
        }

        companyCatService.saveCompany(companyCat);
        attributes.addFlashAttribute("message","Registro se guardo con exito");
        return new RedirectView("/empresas");
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public RedirectView update(CompanyCat companyCat, RedirectAttributes attributes){
        CompanyCat e = companyCatService.getCompany(companyCat.getCompanyName());
        if (e!=null){
            attributes.addFlashAttribute("message","Empresa "+ e.getCompanyName()+" ya existe");
            return new RedirectView("/empresas/editar/"+ companyCat.getId());
        }
        e = companyCatService.getCompany(companyCat.getId());
        e.setCompanyName(companyCat.getCompanyName());
        companyCatService.saveCompany(e);
        attributes.addFlashAttribute("message","Registro modificado con exito");
        return new RedirectView("/empresas");
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public RedirectView delete(CompanyCat companyCat, RedirectAttributes attributes){
        companyCatService.deleteCompany(companyCat);
        attributes.addFlashAttribute("message","Registro se elimino con exito");
        return new RedirectView("/empresas");
    }
}
