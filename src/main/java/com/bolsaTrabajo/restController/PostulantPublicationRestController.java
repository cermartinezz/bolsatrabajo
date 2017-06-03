package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.AcademicTitleCat;
import com.bolsaTrabajo.model.catalog.Publication;
import com.bolsaTrabajo.model.postulantInfo.PostulantPublication;
import com.bolsaTrabajo.service.PostulantPublicationService;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.service.PublicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/postulante/{username}/publicaciones")
public class PostulantPublicationRestController {

    public static final Logger logger = LoggerFactory.getLogger(PostulantPublication.class);


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
    public ResponseEntity<?> store(@Valid @RequestBody PostulantPublication postulantPublication, @PathVariable String username, UriComponentsBuilder uriBuilder){


        Integer id = postulantPublication.getPublication().getId();

        Publication publication = publicationService.findById(id);
        Postulant user = postulantService.findByUsername(username);

        postulantPublication.setPublication(publication);
        postulantPublication.setPostulant(user);

        user.getPostulantPublications().add(postulantPublication);

        try{
            postulantService.save(user);
        }catch (InvalidDataAccessApiUsageException e){
            this.headers.set("message","Ya existe un registro con codigo de publicacion: " + postulantPublication.getCode());
            return new ResponseEntity<String>(headers, HttpStatus.FORBIDDEN);
        }

        this.headers.set("message","Se guardo el registro");
        this.headers.setLocation(uriBuilder.path("/postulante/{username}/perfil").buildAndExpand(user.getUsername()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }
}
