package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.*;
import com.bolsaTrabajo.model.compositeKeys.AcademicExperienceID;
import com.bolsaTrabajo.model.compositeKeys.WorkExperienceID;
import com.bolsaTrabajo.model.postulantInfo.AcademicExperience;
import com.bolsaTrabajo.model.postulantInfo.PostulantLanguage;
import com.bolsaTrabajo.model.postulantInfo.WorkExperience;
import com.bolsaTrabajo.service.*;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by keepercito on 05-01-17.
 */

@RestController
@RequestMapping("/api/postulant")
public class PostulantRestController {


    @Autowired
    private AcademicTitleCatService academicTitleCatService;


    @Autowired
    PostulantService postulantService;

    @Autowired
    private CompanyCatService companyCatService;

    @Autowired
    UserService userService;

    @Autowired
    private JobCatService jobCatService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private LanguageService languageService;


    @Autowired
    private WorkExperienceService workExperienceService;

    @Autowired
    private AcademicExperienceService academicExperienceService;
    private HttpHeaders headers;

    public PostulantRestController() {
        this.headers = new HttpHeaders();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Postulant>> getAll(){
        List<Postulant> postulants = postulantService.getAll();
        List<Postulant> post = new ArrayList<>();


        if(postulants == null || postulants.isEmpty()){
            return new ResponseEntity<List<Postulant>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Postulant>>(postulants, HttpStatus.OK);

    }

    @GetMapping("/language/{lang}")
    public ResponseEntity<List<Postulant>> getAllByLanguage(@PathVariable String lang){
        Language language = languageService.findByCodigo(lang);
        List<PostulantLanguage> elegidos = postulantService.getPostulantForLanguage(language);
        List<Postulant> postulants = new ArrayList(); //Esta lista es la que se regresará
        // Si no te lo regresa por el json ignore, filtralos aqui mismo
        // y eso es lo que tenes que regresar!
        for (PostulantLanguage elegido : elegidos) {
            postulants.add(elegido.getPostulant());
        }
        if(postulants == null || postulants.isEmpty()){
            return new ResponseEntity<List<Postulant>>(HttpStatus.NO_CONTENT);
        }
        // En lugar de regresar el compuesto, regresa el hijo
        return new ResponseEntity<List<Postulant>>(postulants, HttpStatus.OK);

    }

    @GetMapping("/academicState/{state}")
    public ResponseEntity<Set<Postulant>> getAllByAcademicState(@PathVariable String state){

        List<Postulant> postulants = postulantService.getAll(); //Esta lista es la que se regresará

        List<Postulant> filtrados = new ArrayList<>();

        List<Postulant> postState = new ArrayList<>();

        for (Postulant postulant : postulants){
           if(postulant.getStateOfEducation() != null){
               filtrados.add(postulant);
               for (Postulant p : filtrados)
                   if (p.getStateOfEducation().toString().equals(state)){
                   postState.add(p);
                   }
           }
        }

        Set<Postulant> resultado = new HashSet<Postulant>(postState);

        if(postulants == null || postulants.isEmpty()){
            return new ResponseEntity<Set<Postulant>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Set<Postulant>>(resultado, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity show(@PathVariable String username){

        Postulant postulant = postulantService.findByUsername(username);

        if(postulant == null){

            headers.set("message","No se encontraron registros");

            return new ResponseEntity(headers, HttpStatus.NOT_FOUND);
        }

        headers.set("message","Registros Encontrados");

        return new ResponseEntity(postulant,headers, HttpStatus.OK);
    }

    @PutMapping("/{username}/actualizar")
    public ResponseEntity update(@Valid @RequestBody Postulant postulant,
                                 @PathVariable String username,
                                 UriComponentsBuilder ucBuilder){
        Postulant currentPostulant = postulantService.findByUsername(username);

        currentPostulant.setLastName(postulant.getLastName());
        currentPostulant.setNit(postulant.getNit());
        currentPostulant.setDui(postulant.getDui());
        currentPostulant.setUsername(postulant.getUsername());
        currentPostulant.setName(postulant.getName());

        postulantService.update(currentPostulant);

        this.headers.set("message","Postulante actualizado");

        this.headers.setLocation(ucBuilder.path("/postulante/{username}/perfil").buildAndExpand(postulant.getUsername()).toUri());

        return new ResponseEntity(currentPostulant,this.headers, HttpStatus.OK);
    }


    @PostMapping(value = "/ExpLabo")
    public RedirectView storeWorkExp(WorkExperience experience, @RequestParam("empresas") long empresa,
                                     @RequestParam("jobs") long job, @RequestParam("inicio") String inicio,
                                      RedirectAttributes attributes){

        Postulant p = postulantService.findByUsername(Auth.auth().getName());
        CompanyCat c = companyCatService.getCompany(empresa);
        JobCat j = jobCatService.getJob(job);

        experience.getPk().setInicio(inicio);
        experience.setCompanyCat(c);
        experience.setPostulant(p);
        experience.setJobCat(j);

        p.getWorkExperiences().add(experience);
        postulantService.save(p);
        attributes.addFlashAttribute("message","Registro se guardo con exito");
        return new RedirectView("/postulante/"+p.getUsername()+"/perfil");
    }

    @PostMapping(value = "/ExpLabo/actualizar")
    public RedirectView updateWorkExp(WorkExperience experience, @RequestParam("empresas") long empresa,
                                      @RequestParam("jobs") long job, @RequestParam("inicio") String inicio,
                                      @RequestParam("job") long puesto, @RequestParam("empresa") long company,
                                      @RequestParam("begin") String begin, RedirectAttributes attributes){

        WorkExperienceID id = new WorkExperienceID();

        id.setPostulant(postulantService.findByUsername(Auth.auth().getName()));
        id.setCompanyCat(companyCatService.getCompany(company));
        id.setJobCat(jobCatService.getJob(puesto));
        id.setInicio(begin);

        Postulant p = postulantService.findByUsername(Auth.auth().getName());
        WorkExperience w = workExperienceService.getWorkExpById(id);
        workExperienceService.deleteWorkExp(w);
        p.getWorkExperiences().remove(w);
        CompanyCat c = companyCatService.getCompany(empresa);
        JobCat j = jobCatService.getJob(job);

        experience.getPk().setInicio(inicio);
        experience.setCompanyCat(c);
        experience.setPostulant(p);
        experience.setJobCat(j);

        p.getWorkExperiences().add(experience);
        postulantService.save(p);
        attributes.addFlashAttribute("message","Registro se guardo con exito");
        return new RedirectView("/postulante/"+p.getUsername()+"/perfil");
    }

    @GetMapping(value = "/ExpLabo/delete/{company}/{job}/{begin}")
    public ResponseEntity deleteWorkExp(@PathVariable long company, @PathVariable long job, @PathVariable String begin){
        WorkExperienceID id = new WorkExperienceID();
        id.setPostulant(postulantService.findByUsername(Auth.auth().getName()));
        id.setCompanyCat(companyCatService.getCompany(company));
        id.setJobCat(jobCatService.getJob(job));
        id.setInicio(begin);
        workExperienceService.deleteWorkExp(workExperienceService.getWorkExpById(id));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/ExpAcad")
    public RedirectView storeAcadExp(AcademicExperience experience, @RequestParam("institucion") int institucion,
                                     @RequestParam("titles") long title, RedirectAttributes attributes,
                                    HttpServletRequest request){

        Postulant p = postulantService.findByUsername(Auth.auth().getName());
        Institution ins = institutionService.findInstitutionById(institucion);
        AcademicTitleCat acad = academicTitleCatService.getTitle(title);

        experience.setInstitution(ins);
        experience.setPostulant(p);
        experience.setTitle(acad);
        int year = new Date().getYear();
        if(experience.getAñoGraduacion() < year){
            attributes.addFlashAttribute("message","La fecha de graduacion no puede ser mayor a este año");
            return new RedirectView("/postulante/"+Auth.auth().getName()+"/ExpAcad/agregar");
        }
        p.getAcademicExperiences().add(experience);
        try{
            postulantService.save(p);
        }catch (Exception e){
            attributes.addFlashAttribute("message","No se pudo crear, tu ya agregaste este registro");
            return new RedirectView("/postulante/"+Auth.auth().getName()+"/ExpAcad/agregar");
        }

        attributes.addFlashAttribute("message","Registro se guardo con exito");
        return new RedirectView("/postulante/"+p.getUsername()+"/perfil");
    }

    @PostMapping(value = "/ExpAcad/actualizar")
    public RedirectView updateAcadExp(AcademicExperience experience, @RequestParam("institution") int institution,
                                      @RequestParam("titles") long titles, @RequestParam("institucion") int institucion,
                                      @RequestParam("titulo") long titulo, RedirectAttributes attributes){

        AcademicExperienceID id = new AcademicExperienceID();

        id.setPostulant(postulantService.findByUsername(Auth.auth().getName()));
        id.setInstitution(institutionService.findInstitutionById(institucion));
        id.setTitle(academicTitleCatService.getTitle(titulo));

        Postulant p = postulantService.findByUsername(Auth.auth().getName());
        AcademicExperience w = academicExperienceService.getAcadExpById(id);
        academicExperienceService.deleteAcadExp(w);
        p.getAcademicExperiences().remove(w);
        Institution institution1 = institutionService.findInstitutionById(institution);
        AcademicTitleCat acadTitle = academicTitleCatService.getTitle(titles);

        experience.getPk().setTitle(acadTitle);
        experience.getPk().setInstitution(institution1);
        experience.setPostulant(p);

        p.getAcademicExperiences().add(experience);
        postulantService.save(p);
        attributes.addFlashAttribute("message","Registro se guardo con exito");
        return new RedirectView("/postulante/"+p.getUsername()+"/perfil");
    }

    @GetMapping(value = "/ExpAcad/delete/{titulo}/{institucion}")
    public ResponseEntity deleteWorkExp(@PathVariable long titulo, @PathVariable int institucion){
        AcademicExperienceID id = new AcademicExperienceID();
        id.setPostulant(postulantService.findByUsername(Auth.auth().getName()));
        id.setInstitution(institutionService.findInstitutionById(institucion));
        id.setTitle(academicTitleCatService.getTitle(titulo));
        academicExperienceService.deleteAcadExp(academicExperienceService.getAcadExpById(id));
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{username}/applications")
    public ResponseEntity showApplications(@PathVariable String username){
        Postulant postulant = postulantService.findByUsername(username);
        postulant.getCandidates();

        if(postulant == null){

            headers.set("message","No se encontraron registros");

            return new ResponseEntity(headers, HttpStatus.NOT_FOUND);
        }

        headers.set("message","Registros Encontrados");

        return new ResponseEntity(postulant,headers, HttpStatus.OK);
    }
}
