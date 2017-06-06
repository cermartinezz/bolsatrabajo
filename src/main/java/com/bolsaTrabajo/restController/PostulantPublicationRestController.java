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
import java.util.List;

@RestController
@RequestMapping("/api/postulante/{username}/publicaciones")
public class PostulantPublicationRestController {

    public static final Logger logger = LoggerFactory.getLogger(PostulantPublication.class);

    @Autowired
    private PostulantPublication postulantPublication;

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

    @GetMapping
    public ResponseEntity<List<PostulantPublication>> getPublications(@PathVariable String username){
        return new ResponseEntity<List<PostulantPublication>>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostulantPublication> store(@Valid @RequestBody PostulantPublication postulantPublicationFromRequest, @PathVariable String username, UriComponentsBuilder uriBuilder){

        PostulantPublication publication;

        try{
            publication = this.postulantPublication.save(username,postulantPublicationFromRequest);
            //publication = new PostulantPublication().save(username,postulantPublicationFromRequest);
        }catch (InvalidDataAccessApiUsageException e){
            this.headers.set("message","No se pudo registrar la habilidad, porque ya existe asociada a este usuario");
            return new ResponseEntity<PostulantPublication>(this.headers,HttpStatus.CONFLICT);
        }

        this.headers.set("message","Se guardo el registro");
        this.headers.setLocation(uriBuilder.path("/postulante/{username}/perfil").buildAndExpand(username).toUri());
        return new ResponseEntity<PostulantPublication>(publication,this.headers, HttpStatus.CREATED);

    }
    @DeleteMapping("/{code}/eliminar")
    public ResponseEntity<?> delete(@PathVariable String username,@PathVariable String code){

        boolean eliminado = postulantPublication.delete(username, code);

        if(eliminado){
            this.headers.set("message","Se elimino la habilidad");
            return new ResponseEntity(this.headers,HttpStatus.OK);
        }

        this.headers.set("message","No se elimino la habilidad");
        return new ResponseEntity(this.headers,HttpStatus.CONFLICT);

    }
}
