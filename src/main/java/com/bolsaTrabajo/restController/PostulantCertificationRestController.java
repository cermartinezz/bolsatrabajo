package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.catalog.Certification;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.postulantInfo.PostulantCertification;
import com.bolsaTrabajo.service.CertificationService;
import com.bolsaTrabajo.service.PostulantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/postulante/{username}/certificaciones")
public class PostulantCertificationRestController {

    public static final Logger logger = LoggerFactory.getLogger(PostulantCertification.class);

    @Autowired
    private PostulantService postulantService;

    @Autowired
    private CertificationService certificationService;

    private HttpHeaders headers;

    public PostulantCertificationRestController() {
        this.headers = new HttpHeaders();
    }

    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody PostulantCertification postulantCertification,
                                   @PathVariable String username,
                                   UriComponentsBuilder uriBuilder,
                                   Errors errors){

        Integer id = postulantCertification.getCertification().getCertificationId();

        Certification certification = certificationService.findCertificationById(id);

        Postulant user = postulantService.findByUsername(username);

        postulantCertification.setCertification(certification);
        postulantCertification.setPostulant(user);

        logger.info("postulat certification {}", postulantCertification);
        logger.info("postulante {}", user);
        logger.info("certificacion {}", certification);

        user.getCertifications().add(postulantCertification);

        try {
            postulantService.save(user);
        }catch (InvalidDataAccessApiUsageException e){
            this.headers.set("message","Ya existe un registro con codigo de certificacion: " + postulantCertification.getCode());
            return new ResponseEntity<String>(headers, HttpStatus.CONFLICT);
        }

        this.headers.set("message","Se guardo el registro");
        this.headers.setLocation(uriBuilder.path("/postulante/{username}/perfil").buildAndExpand(user.getUsername()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }
}
