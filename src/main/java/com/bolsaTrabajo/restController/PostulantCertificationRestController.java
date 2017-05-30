package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.Certification;
import com.bolsaTrabajo.model.compositeKeys.PostulantCertificationId;
import com.bolsaTrabajo.model.postulantInfo.PostulantCertification;
import com.bolsaTrabajo.service.CertificationService;
import com.bolsaTrabajo.service.PostulantCertificationService;
import com.bolsaTrabajo.service.PostulantService;
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
@RequestMapping("/api/postulante/{username}/certificaciones")
public class PostulantCertificationRestController {

    public static final Logger logger = LoggerFactory.getLogger(PostulantCertification.class);

    @Autowired
    private PostulantService postulantService;

    @Autowired
    PostulantCertificationService postulantCertificationService;

    @Autowired
    private CertificationService certificationService;

    @Autowired
    private PostulantCertification postulantCertification;


    private HttpHeaders headers;

    public PostulantCertificationRestController() {
        this.headers = new HttpHeaders();
    }


    @GetMapping("/codigo/{code}/certificacion/{certificationId}")
    public ResponseEntity<PostulantCertification> getCertificationOfPostulant(@PathVariable String username,
                                                         @PathVariable Integer certificationId,
                                                         @PathVariable String code){

        Certification certification = certificationService.findCertificationById(certificationId);

        Postulant postulant = postulantService.findByUsername(username);

        PostulantCertificationId primaryKey = new PostulantCertificationId(postulant,certification,code);

        PostulantCertification postulant_certification = postulantCertificationService.getCertificationOfPostulant(primaryKey);

        if(postulant_certification.equals(null)){
            this.headers.set("message", "La certificacion no ha sido encontrada");

            return new ResponseEntity<PostulantCertification>(this.headers,HttpStatus.NOT_FOUND);
        }

        this.headers.set("message", "La certificacion ha sido encontrada");

        return new ResponseEntity<PostulantCertification>(postulant_certification,this.headers,HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody PostulantCertification postulantCertification,
                                   @PathVariable String username,
                                   UriComponentsBuilder uriBuilder){

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
            return new ResponseEntity<String>(headers, HttpStatus.FORBIDDEN);
        }

        this.headers.set("message","Se guardo el registro");
        this.headers.setLocation(uriBuilder.path("/postulante/{username}/perfil").buildAndExpand(user.getUsername()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }

    @PutMapping("/codigo/{code}/certificacion/{certificationId}/actualizar")
    public ResponseEntity<?> update(@RequestBody PostulantCertification freshCertification,
                                    @PathVariable String username,
                                    @PathVariable Integer certificationId,
                                    @PathVariable String code,
                                    UriComponentsBuilder uriBuilder){

        Postulant postulant = postulantService.findByUsername(username);

        Certification certification = certificationService.findCertificationById(certificationId);

        this.postulantCertification.update(postulant,certification,code,freshCertification);

        this.headers.set("message","Se actualizo el registro");

        this.headers.setLocation(uriBuilder.path("/postulante/{username}/perfil").buildAndExpand(username).toUri());

        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }

    @DeleteMapping("/codigo/{code}/certificacion/{certificationId}/eliminar")
    public ResponseEntity<?> delete(@PathVariable String username,
                                    @PathVariable Integer certificationId,
                                    @PathVariable String code){

        Postulant postulant = postulantService.findByUsername(username);

        Certification certification = certificationService.findCertificationById(certificationId);

        this.postulantCertification.delete(postulant,certification,code);

        this.headers.set("message","Se Elimino la Certificacion");

        return new ResponseEntity<String>(headers, HttpStatus.OK);

    }
}
