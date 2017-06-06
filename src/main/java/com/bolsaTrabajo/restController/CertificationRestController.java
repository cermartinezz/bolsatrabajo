package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.catalog.Certification;
import com.bolsaTrabajo.service.CertificationService;
import com.bolsaTrabajo.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> store(@Valid @RequestBody Certification certification,
                                   UriComponentsBuilder ucBuilder) {

        try{

            certificationService.spNewCertification(certification);

        }catch(Exception e){
            // Esto es porq regresa ora-20001 mensaje importante ora-20001 "spring....."
            // solo deja el mensaje importante
            String message = StringUtils.clearMessage(e.getCause().getCause().getMessage());

            this.headers.set("message", message);

            return new ResponseEntity<String>(this.headers, HttpStatus.CONFLICT);
        }

        this.headers.setLocation(ucBuilder.path("/certificaciones").build().toUri());

        this.headers.set("message", "Se guardo la certificacion");

        return new ResponseEntity<String>(this.headers, HttpStatus.CREATED);

    }

    @PutMapping(value = "/{code}")
    public ResponseEntity update(@PathVariable("code") String code,
                                 @Valid @RequestBody Certification certificationFromRequest,
                                 UriComponentsBuilder uriBuilder) {

        Certification certification = certificationService.findCertificationById(certificationFromRequest.getCertificationId());

       certification.setCertificationTitle(certificationFromRequest.getCertificationTitle());

       certification.setInstitution(certificationFromRequest.getInstitution());

       try{

           certificationService.spUpdateCertification(certification);

       }catch (Exception e){

           String message = StringUtils.clearMessage(e.getCause().getCause().getMessage());

           this.headers.set("message", message);

           return new ResponseEntity(certification,this.headers, HttpStatus.CONFLICT);
       }

       this.headers.setLocation(uriBuilder.path("/certificaciones").build().toUri());

       return new ResponseEntity(certification,this.headers, HttpStatus.OK);
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
