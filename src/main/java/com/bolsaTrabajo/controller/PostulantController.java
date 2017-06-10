package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.Institution;
import com.bolsaTrabajo.model.compositeKeys.AcademicExperienceID;
import com.bolsaTrabajo.model.compositeKeys.WorkExperienceID;
import com.bolsaTrabajo.model.postulantInfo.AcademicExperience;
import com.bolsaTrabajo.model.postulantInfo.WorkExperience;
import com.bolsaTrabajo.service.*;
import com.bolsaTrabajo.util.Auth;
import com.bolsaTrabajo.util.InstitutionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/postulante/{username}")
public class PostulantController {

    @Autowired
    private PostulantService postulantService;

    @Autowired
    private InstitutionService institutionService;


    @Autowired
    private CompanyCatService companyCatService;

    @Autowired
    private JobCatService jobCatService;

    @Autowired
    private AcademicTitleCatService titleCatService;

    @Autowired
    private WorkExperienceService workExperienceService;

    @Autowired
    private AcademicExperienceService academicExperienceService;

    public Postulant postulant;


    @GetMapping("perfil")
    public String profile(Model model, @PathVariable String username){

        if( !username.equals("anonymousUser") ){
            model.addAttribute("user", Auth.auth());

            this.postulant = postulantService.findByUsername(username);

            model.addAttribute("postulantInfo", this.postulant);
            model.addAttribute("weID", new WorkExperienceID());
            return "Postulante/profile";
        }
        return "redirect:/";
    }

    @GetMapping("editar")
    public String edit(Model model, @PathVariable String username){

        model.addAttribute("user", Auth.auth());

        this.postulant = postulantService.findByUsername(username);

        model.addAttribute("postulantInfo", this.postulant);

        return "Postulante/editar";
    }

    @GetMapping("/certificaciones/agregar")
    public String certificaciones(Model model, @PathVariable String username){

        List<Institution> institution = new ArrayList<>();

        List<Institution> institutions = institutionService.getAllInstitutions();

        model.addAttribute("institution", institution);

        model.addAttribute("institutions", institutions);


        model.addAttribute("user", Auth.auth());

        model.addAttribute("postulantInfo", postulantService.findByUsername(username));

        return "Postulante/certificaciones/crear";
    }

    @GetMapping("/certificaciones/{code}/{certificationId}/editar")
    public String certificationesEditar(Model model,
                                        @PathVariable String username,
                                        @PathVariable Integer certificationId,
                                        @PathVariable String code){

        Postulant postulant = postulantService.findByUsername(username);

        List<Institution> institution = new ArrayList<>();

        List<Institution> institutions = institutionService.getAllInstitutions();


        model.addAttribute("institution", institution);

        model.addAttribute("institutions", institutions);

        model.addAttribute("user", Auth.auth());

        model.addAttribute("postulantInfo", postulant);

        model.addAttribute("id",certificationId);

        model.addAttribute("code",code);

        return "Postulante/certificaciones/editar";
    }

    @RequestMapping("/workExp/agregar")
    public String workExperienceStore(Model model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("companies",companyCatService.getAllCompanies());
        model.addAttribute("jobs",jobCatService.getAllJobs());
        model.addAttribute("workExp", new WorkExperience());
        return "Postulante/exp_labo/create_workExp";
    }

    @RequestMapping(value = "/workExp/editar", method = RequestMethod.POST)
    public String workExperienceEdit(Model model, @RequestParam("inicio") String inicio, @RequestParam("company") long company,
                                     @RequestParam("job") long job){
        WorkExperienceID id = new WorkExperienceID();
        id.setInicio(inicio);
        id.setPostulant(postulantService.findByUsername(Auth.auth().getName()));
        id.setJobCat(jobCatService.getJob(job));
        id.setCompanyCat(companyCatService.getCompany(company));
        WorkExperience experience = workExperienceService.getWorkExpById(id);
        model.addAttribute("user", Auth.auth());
        model.addAttribute("companies",companyCatService.getAllCompanies());
        model.addAttribute("jobs",jobCatService.getAllJobs());
        model.addAttribute("workExp", experience);
        return "Postulante/exp_labo/edit_workExp";
    }

    @RequestMapping("/acadExp/agregar")
    public String academicExperience(Model model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("institutions",institutionService.getInstitutionsByType(InstitutionType.Academica));
        model.addAttribute("titles",titleCatService.getAllTitles());
        model.addAttribute("acadExp", new AcademicExperience());
        return "Postulante/exp_acad/create_acadExp";
    }

    //editar para experiencia academica
    @RequestMapping(value = "/acadExp/editar", method = RequestMethod.POST)
    public String acadExperienceEdit(Model model, @RequestParam("titulo") int titulo,
                                     @RequestParam("institucion") int institucion){
        AcademicExperienceID id = new AcademicExperienceID();
        id.setPostulant(postulantService.findByUsername(Auth.auth().getName()));
        id.setInstitution(institutionService.findInstitutionById(institucion));
        id.setTitle(titleCatService.getTitle(titulo));
        AcademicExperience experience = academicExperienceService.getAcadExpById(id);
        model.addAttribute("user", Auth.auth());
        model.addAttribute("titles",titleCatService.getAllTitles());
        model.addAttribute("institutions",institutionService.getInstitutionsByType(InstitutionType.Academica));
        model.addAttribute("acadExp", experience);
        return "Postulante/exp_acad/edit_acadExp";
    }

}
