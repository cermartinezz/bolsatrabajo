package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.*;
import com.bolsaTrabajo.repositories.PostulantRepository;
import com.bolsaTrabajo.repositories.UserRepository;
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
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 * Created by keepercito on 05-01-17.
 */

@RestController
@RequestMapping("/api/postulant")
public class PostulantRestController {

    @Autowired
    PostulantService postulantService;

    @Autowired
    UserService userService;

    @Autowired
    CompanyCatService companyCatService;

    private HttpHeaders headers;

    public PostulantRestController() {
        this.headers = new HttpHeaders();
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

    @PostMapping()
    public String storeWorkExp(WorkExperience experience, @RequestParam("empresas") long empresa, RedirectAttributes attributes){

        Postulant p = postulantService.findByUsername(Auth.auth().getName());
        CompanyCat c = companyCatService.getCompany(empresa);

        experience.setCompany(c);
        experience.setPostulant(p);

        //p.getWorkExperiences().add(experience);
        userService.save(p);
        return "redirect:/postulant/profile";
    }
}
