package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.PostulantPublication;
import com.bolsaTrabajo.model.Publication;
import com.bolsaTrabajo.service.PostulantPublicationService;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Created by mvip on 05-30-17.
 */
@RestController
@RequestMapping("/api/postulante/{username}/publicaciones")
public class PostulantPublicationRestController {

    @Autowired
    private PostulantService postulantService;

    @Autowired
    private PublicationService publicationService;

    private HttpHeaders headers;

    @Autowired
    private PostulantPublicationService postulantPublicationService;

    public  PostulantPublicationRestController(){
        this.headers= new HttpHeaders();
    }

    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody PostulantPublication postulantPublication, @PathVariable String username){


        Integer id = postulantPublication.getPublication().getId();

        Publication publication = publicationService.findById(id);
        Postulant user = postulantService.findByUsername(username);

        postulantPublication.setPublication(publication);
        postulantPublication.setPostulant(user);


        postulantPublicationService.store(postulantPublication);

        this.headers.set("message","Se guardo el registro");

        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }
}
