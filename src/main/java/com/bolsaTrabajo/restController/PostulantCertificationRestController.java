package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.PostulantCertification;
import com.bolsaTrabajo.service.PostulantCertificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/postulante/{username}/certificaciones")
public class PostulantCertificationRestController {

    public static final Logger logger = LoggerFactory.getLogger(PostulantCertification.class);

    private HttpHeaders headers;

    @Autowired
    private PostulantCertificationService postulantCertificationService;



    public PostulantCertificationRestController() {
        this.headers = new HttpHeaders();
    }

    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody PostulantCertification postulantCertification, @PathVariable String username){

        postulantCertificationService.save(postulantCertification);

        this.headers.set("message","Se guardo el registro");

        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }
}
