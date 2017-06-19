package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.*;
import com.bolsaTrabajo.model.catalog.*;
import com.bolsaTrabajo.model.postulantInfo.AcademicExperience;
import com.bolsaTrabajo.model.postulantInfo.PostulantLanguage;
import com.bolsaTrabajo.model.postulantInfo.WorkExperience;
import com.bolsaTrabajo.service.*;
import com.bolsaTrabajo.service.CompanyCatService;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.service.UserService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

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

    private HttpHeaders headers;

    public PostulantRestController() {
        this.headers = new HttpHeaders();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Postulant>> getAll(){
        List<Postulant> postulants = postulantService.getAll();
        if(postulants == null || postulants.isEmpty()){
            return new ResponseEntity<List<Postulant>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Postulant>>(postulants, HttpStatus.OK);

    }

    @GetMapping("/language/{lang}")
    public ResponseEntity<List<PostulantLanguage>> getAllByLanguage(@PathVariable String lang){
        Language language = languageService.findByCodigo(lang);
        List<PostulantLanguage> postulants = postulantService.getPostulantForLanguage(language);
        if(postulants == null || postulants.isEmpty()){
            return new ResponseEntity<List<PostulantLanguage>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<PostulantLanguage>>(postulants, HttpStatus.OK);

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
        AcademicTitleCat acad = academicTitleCatService.getTitle(title);

        experience.setInstitution(ins);
        experience.setPostulant(p);
        experience.setTitle(acad);

        p.getAcademicExperiences().add(experience);
        postulantService.save(p);
        //attributes.addFlashAttribute("message","Registro se guardo con exito");
        return new RedirectView("/postulante/"+p.getUsername()+"/perfil");
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
