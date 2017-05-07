package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.Certification;
import com.bolsaTrabajo.model.Institution;
import com.bolsaTrabajo.service.CertificationService;
import com.bolsaTrabajo.service.InstitutionService;
import com.bolsaTrabajo.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/certificaciones")
public class CertificationController {

    public static final Logger logger = LoggerFactory.getLogger(CertificationService.class);
    public static final String CREATE_CERTIFICATIONS = "certificaciones/create_certifications";
    public static final String EDIT_CERTIFICATIONS = "certificaciones/edit_certifications";
    public static final String CERTIFICATION = "certification";


    @Autowired
    private CertificationService certificationService;

    @Autowired
    private InstitutionService institutionService;

    @RequestMapping
    public String index(Model model){

        model.addAttribute("user",Auth.auth());

        model.addAttribute("certificaciones", certificationService.getAllCertifications());

        return "certificaciones/index_certifications";

    }

    @GetMapping(value = "{code}")
    public String show(Model model, @PathVariable String code){

        model.addAttribute("user",Auth.auth());

        model.addAttribute("certification",certificationService.findCertificationByCode(code));

        return "certificaciones/show_certifications";
    }

    @GetMapping(value="/crear")
    public String create(Model model){

        List<Institution> institution = new ArrayList<>();

        List<Institution> institutions = institutionService.getAllInstitutions();

        model.addAttribute("certification",new Certification());

        model.addAttribute("user", Auth.auth());

        model.addAttribute("institution", institution);

        model.addAttribute("institutions", institutions);

        return "certificaciones/create_certifications";

    }

    @RequestMapping(value = "/{code}/editar", method = RequestMethod.GET)
    public String edit(@PathVariable String code, Model model){

        Certification certification = certificationService.findCertificationByCode(code);

        List<Institution> institution = new ArrayList<>();

        List<Institution> institutions = institutionService.getAllInstitutions();


        model.addAttribute("user", Auth.auth());

        model.addAttribute("certification", certification);

        model.addAttribute("institution", institution);

        model.addAttribute("institutions", institutions);

        return "certificaciones/edit_certifications";

    }

}
