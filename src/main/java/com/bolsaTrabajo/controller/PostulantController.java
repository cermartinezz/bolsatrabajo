package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.Job;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.Institution;
import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.model.compositeKeys.CandidateId;
import com.bolsaTrabajo.model.jobInfo.Candidate;
import com.bolsaTrabajo.model.postulantInfo.AcademicExperience;
import com.bolsaTrabajo.model.postulantInfo.WorkExperience;
import com.bolsaTrabajo.service.*;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/workExp/agregar")
    public String workExperience(Model model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("companies",companyCatService.getAllCompanies());
        model.addAttribute("jobs",jobCatService.getAllJobs());
        model.addAttribute("workExp", new WorkExperience());
        return "Postulante/exp_labo/create_workExp";
    }

    @RequestMapping("/acadExp/agregar")
    public String academicExperience(Model model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("institutions",institutionService.getAllInstitutions());
        model.addAttribute("titles",titleCatService.getAllTitles());
        model.addAttribute("acadExp", new AcademicExperience());
        return "Postulante/exp_acad/create_acadExp";
    }

    @GetMapping("aplicaciones")
    public String applications(Model model, @PathVariable String username){

        Postulant postulantFromRequest = postulantService.findByUsername(username);
        postulantFromRequest.getCandidates();

        //List<Candidate> candidate = candidateService.getJobOfPostulant()
        if( !username.equals("anonymousUser") ){
            model.addAttribute("user", Auth.auth());
            this.postulant = postulantService.findByUsername(username);
            model.addAttribute("postulantInfo", this.postulant);
            model.addAttribute("candidate",postulantFromRequest);
            return "Postulante/aplicaciones";
        }
        return "redirect:/";
    }

}
