package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Certification;
import com.bolsaTrabajo.model.Publication;
import com.bolsaTrabajo.service.PublicationService;
import com.bolsaTrabajo.util.CustomErrorType;
import com.bolsaTrabajo.validator.PublicationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by mvip on 04-04-17.
 */
@RestController
@RequestMapping(value = "/publications")
public class PublicationRestController {

    public static final Logger logger = LoggerFactory.getLogger(PublicationRestController.class);

    private HttpHeaders headers;

    @Autowired
    private PublicationValidator publicationValidator;

    @Autowired
    public PublicationService publicationService;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(publicationValidator);
    }


    public PublicationRestController() {
        this.headers = new HttpHeaders();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Publication>> getAll() {
        List<Publication> publications = publicationService.getAllPublications();

        if (publications == null || publications.isEmpty()) {
            return new ResponseEntity<List<Publication>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Publication>>(publications, HttpStatus.OK);
    }

    @GetMapping(value= "{code}")
    public ResponseEntity show(@PathVariable("code") String code){

        Publication publication = publicationService.findPublicationByCodigo(code);

        if(publication == null){
            headers.set("message","No se encontraron registros");
            return new ResponseEntity(headers,HttpStatus.NOT_FOUND);
        }
        headers.set("message","Registros Encontrados");
        return new ResponseEntity(publication,headers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid @RequestBody Publication publication, UriComponentsBuilder ucBuilder){

        publicationService.storePublication(publication);

        this.headers.setLocation(ucBuilder.path("/publicaciones").buildAndExpand(publication.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.PUT)
    public ResponseEntity actualizar(@PathVariable("code") String code, @Valid @RequestBody Publication publication,UriComponentsBuilder ucBuilder) {
        Publication current = publicationService.findPublicationByCodigo(code);

        current.setTitulo(publication.getTitulo());
        current.setEditorial(publication.getEditorial());
        current.setTipo(publication.getTipo());

        publicationService.updatePublication(current);
        this.headers.setLocation(ucBuilder.path("/publicaciones").buildAndExpand(publication.getId()).toUri());

        return new ResponseEntity(current,headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{code}",method = RequestMethod.DELETE)
    public ResponseEntity eliminar(@PathVariable("code") String code){
        Publication publication = publicationService.findPublicationByCodigo(code);
        if (publication == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        publicationService.deletePublication(code);

        return  new ResponseEntity<Publication>(HttpStatus.NO_CONTENT);
    }
}
