package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.postulantInfo.Publication;
import com.bolsaTrabajo.service.PublicationService;
import com.bolsaTrabajo.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by mvip on 04-04-17.
 */
@RestController
@RequestMapping(value = "/publications")
public class PublicationRestController {

    public static final Logger logger = LoggerFactory.getLogger(PublicationRestController.class);

    @Autowired
    public PublicationService publicationService;
/*
    @Autowired
    public PublicationRepository publicationRepository;
*/
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Publication>> getAll() {
        List<Publication> publications = publicationService.getAllPublications();

        if (publications == null || publications.isEmpty()) {
            return new ResponseEntity<List<Publication>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Publication>>(publications, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Publication publication, UriComponentsBuilder ucBuilder){

        Publication pub = publicationService.findPublicationByCodigo(publication.getCodigo());
        if (pub != null){
            return new ResponseEntity<>(new CustomErrorType("La publicacion ya existe"), HttpStatus.CONFLICT);
        }
        publicationService.storePublication(publication);

        //publicationRepository.save(publication);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/publications/{id}").buildAndExpand(publication.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.PUT)
    public ResponseEntity actualizar(@PathVariable("code") String code, @RequestBody Publication publication) {
        Publication current = publicationService.findPublicationByCodigo(code);

        if (current == null) {
            return new ResponseEntity(new CustomErrorType("La publication que desea actualizar no existe"), HttpStatus.NOT_FOUND);
        }

        current.setTitulo(publication.getTitulo());
        current.setEditorial(publication.getEditorial());
        current.setTipo(publication.getTipo());

        publicationService.updatePublication(current);

        return new ResponseEntity<Publication>(current, HttpStatus.OK);
    }
}
