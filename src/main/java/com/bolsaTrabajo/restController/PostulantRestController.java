package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.*;
import com.bolsaTrabajo.model.catalog.AcademicTitleCat;
import com.bolsaTrabajo.model.catalog.CompanyCat;
import com.bolsaTrabajo.model.catalog.Institution;
import com.bolsaTrabajo.model.catalog.JobCat;
import com.bolsaTrabajo.model.postulantInfo.AcademicExperience;
import com.bolsaTrabajo.model.postulantInfo.WorkExperience;
import com.bolsaTrabajo.service.*;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by keepercito on 05-01-17.
 */

@RestController
@RequestMapping("/api/postulant")
public class PostulantRestController {

    @Autowired
    private PostulantService postulantService;

    @Autowired
    private CompanyCatService companyCatService;

    @Autowired
    private JobCatService jobCatService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private AcademicTitleCatService academicTitleCatService;

    @PostMapping(value = "/workExp")
    public RedirectView storeWorkExp(WorkExperience experience, @RequestParam("empresas") long empresa,
                                     @RequestParam("jobs") long job, RedirectAttributes attributes){

        Postulant p = postulantService.findByUsername(Auth.auth().getName());
        CompanyCat c = companyCatService.getCompany(empresa);
        JobCat j = jobCatService.getJob(job);

        experience.setCompanyCat(c);
        experience.setPostulant(p);
        experience.setJobCat(j);

        p.getWorkExperiences().add(experience);
        postulantService.save(p);
        //attributes.addFlashAttribute("message","Registro se guardo con exito");
        return new RedirectView("/postulante/"+p.getUsername()+"/perfil");
    }

    @PostMapping(value = "/acadExp")
    public RedirectView storeAcadExp(AcademicExperience experience, @RequestParam("institucion") int institucion,
                                     @RequestParam("titles") long title, RedirectAttributes attributes){

        Postulant p = postulantService.findByUsername(Auth.auth().getName());
        Institution ins = institutionService.findInstitutionById(institucion).get();
        AcademicTitleCat acad = academicTitleCatService.getTitle(title).get();

        experience.setInstitution(ins);
        experience.setPostulant(p);
        experience.setTitle(acad);

        p.getAcademicExperiences().add(experience);
        postulantService.save(p);
        //attributes.addFlashAttribute("message","Registro se guardo con exito");
        return new RedirectView("/postulante/"+p.getUsername()+"/perfil");
    }
}
