package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Certification;
import com.bolsaTrabajo.service.CertificationService;
import com.bolsaTrabajo.validator.CertificationValidator;
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

@RestController
@RequestMapping("/api/certificaciones")
public class CertificationRestController {

    public static final Logger logger = LoggerFactory.getLogger(CertificationRestController.class);

    private HttpHeaders headers;

    @Autowired
    private CertificationService certificationService;


    @Autowired
    private CertificationValidator certificationValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(certificationValidator);
    }

    public CertificationRestController() {
        this.headers = new HttpHeaders();
    }

    @GetMapping
    public ResponseEntity<List<Certification>> index() {

        List<Certification> certifications = certificationService.getAllCertifications();

        if (certifications == null || certifications.isEmpty()) {
            return new ResponseEntity<List<Certification>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Certification>>(certifications, HttpStatus.OK);
    }

    @GetMapping(value= "{code}")
    public ResponseEntity show(@PathVariable("code") String code){

        Certification certification = certificationService.findCertificationByCode(code);

        if(certification == null){

            headers.set("message","No se encontraron registros");

            return new ResponseEntity(headers,HttpStatus.NOT_FOUND);
        }

        headers.set("message","Registros Encontrados");

        return new ResponseEntity(certification,headers, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody Certification certification, UriComponentsBuilder ucBuilder) {

        certificationService.storeCertification(certification);

        this.headers.setLocation(ucBuilder.path("/certificaciones").build().toUri());

        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }

    @PutMapping(value = "/{code}")
    public ResponseEntity update(@PathVariable("code") String code,
                                 @Valid @RequestBody Certification certification,
                                 UriComponentsBuilder uriBuilder) {

        Certification currentCertification = certificationService.findCertificationByCode(code);

       currentCertification.setCertificationTitle(certification.getCertificationTitle());

       certificationService.updateCertification(currentCertification);

       this.headers.setLocation(uriBuilder.path("/certificaciones").build().toUri());

       return new ResponseEntity(currentCertification,this.headers, HttpStatus.OK);
    }

    @GetMapping("/institucion/{institution}")
    public ResponseEntity<List<Certification>> getCertificationsByInstitution(@PathVariable Integer institution){

        List<Certification> certifications = certificationService.getCertificationsByInstitution(institution);

        logger.info("certificaciones tamaño: {}", certifications.size());

        if (certifications == null || certifications.isEmpty()) {

            this.headers.set("message", "NO se encontraron certificaciones");

            return new ResponseEntity<List<Certification>>(this.headers,HttpStatus.NO_CONTENT);
        }

        this.headers.set("message", "Se encontraron Certificaciones de la institucion: "+ institution);

        return new ResponseEntity<List<Certification>>(certifications,this.headers, HttpStatus.OK);
    }



}
