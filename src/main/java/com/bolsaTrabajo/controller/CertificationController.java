package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.Certification;
import com.bolsaTrabajo.service.CertificationService;
import com.bolsaTrabajo.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CertificationController {

    public static final String CREATE_CERTIFICATIONS = "certificaciones/create_certifications";
    public static final String EDIT_CERTIFICATIONS = "certificaciones/edit_certifications";
    public static final String CERTIFICATION = "certification";
    public static final Logger logger = LoggerFactory.getLogger(CertificationService.class);


    @Autowired
    private CertificationService certificationService;

    @RequestMapping("/cat/certificaciones")
    public String show(Model model){
        model.addAttribute("user",Auth.auth());
        return "certificaciones/show_certifications";
    }

    @RequestMapping(value="/cat/certificaciones/crear", method = RequestMethod.GET)
    public String create(ModelMap modelMap){
        /*ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(CERTIFICATION, new Certification());
        modelAndView.setViewName(CREATE_CERTIFICATIONS);
        return modelAndView;*/
        modelMap.addAttribute(CERTIFICATION,new Certification());
        modelMap.addAttribute("user", Auth.auth());

        return "certificaciones/create_certifications";

    }

    @RequestMapping(value = "/cat/certificaciones/{code}/editar", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String code){
        ModelAndView modelAndView = new ModelAndView();
        Certification certification = certificationService.findCertificationByCode(code);
        modelAndView.addObject(CERTIFICATION, certification);
        modelAndView.setViewName(EDIT_CERTIFICATIONS);
        return modelAndView;
    }

}
