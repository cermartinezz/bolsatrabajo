package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.Job;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.Institution;
import com.bolsaTrabajo.model.compositeKeys.AcademicExperienceID;
import com.bolsaTrabajo.model.compositeKeys.WorkExperienceID;
import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.model.catalog.Institution;
import com.bolsaTrabajo.model.compositeKeys.CandidateId;
import com.bolsaTrabajo.model.jobInfo.Candidate;
import com.bolsaTrabajo.model.postulantInfo.AcademicExperience;
import com.bolsaTrabajo.model.postulantInfo.WorkExperience;
import com.bolsaTrabajo.service.*;
import com.bolsaTrabajo.util.Auth;
import com.bolsaTrabajo.util.InstitutionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


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
    UserService userService;

    @Autowired
    private CompanyCatService companyCatService;

    @Autowired
    private JobCatService jobCatService;

    @Autowired
    private AcademicTitleCatService titleCatService;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private WorkExperienceService workExperienceService;

    @Autowired
    private AcademicExperienceService academicExperienceService;

    public Postulant postulant;

    @ModelAttribute("usr")
    public User globalUser(Model model) {
        User u = userService.findByUsername(Auth.auth().getName());
        return u;
    }

    @GetMapping("perfil")
    public String profile(Model model, @PathVariable String username){

        if( !username.equals("anonymousUser") ){
            model.addAttribute("user", Auth.auth());

            this.postulant = postulantService.findByUsername(username);

            model.addAttribute("postulantInfo", this.postulant);

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

    @RequestMapping("/ExpLabo/agregar")
    public String workExperienceStore(Model model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("companies",companyCatService.getAllCompanies());
        model.addAttribute("jobs",jobCatService.getAllJobs());
        model.addAttribute("workExp", new WorkExperience());
        return "Postulante/exp_labo/create_workExp";
    }

    @RequestMapping(value = "/ExpLabo/editar", method = RequestMethod.POST)
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

    @RequestMapping("/ExpAcad/agregar")
    public String academicExperience(Model model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("institutions",institutionService.getInstitutionsByType(InstitutionType.Academica));
        model.addAttribute("titles",titleCatService.getAllTitles());
        model.addAttribute("acadExp", new AcademicExperience());
        return "Postulante/exp_acad/create_acadExp";
    }

    @GetMapping("/aplicaciones")
    public String applications(Model model, @PathVariable String username){

        Postulant postulantFromRequest = postulantService.findByUsername(username);
        CandidateId candidatos = new CandidateId();
        candidatos.setPostulant(postulantFromRequest);

        List<Candidate> candidates = candidateService.getJobForPostulant(postulantFromRequest);
        List<Job> jobs = new ArrayList<>();

        for (Candidate candidate :
                candidates) {
            jobs.add(candidate.getPrimaryKey().getJob());
        }
        // si no te funciona del otro lado, hace lo mismo, en el server si salen, pero no se porq no
        // se pasan a la vista

        if( !username.equals("anonymousUser") ){
            model.addAttribute("user", Auth.auth());
            this.postulant = postulantService.findByUsername(username);
            model.addAttribute("postulantInfo", this.postulant);
            model.addAttribute("jobs",jobs);
            return "Postulante/aplicaciones";
        }
        return "redirect:/";
    }

    //editar para experiencia academica
    @RequestMapping(value = "/ExpAcad/editar", method = RequestMethod.POST)
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
