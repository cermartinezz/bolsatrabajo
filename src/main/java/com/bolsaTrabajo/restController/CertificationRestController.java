package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Certification;
import com.bolsaTrabajo.service.CertificationService;
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

@RestController
@RequestMapping("/certifications")
public class CertificationRestController {

    public static final Logger logger = LoggerFactory.getLogger(CertificationRestController.class);

    @Autowired
    public CertificationService certificationService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Certification>> getAll() {
        List<Certification> certifications = certificationService.getAllCertifications();

        if (certifications == null || certifications.isEmpty()) {
            return new ResponseEntity<List<Certification>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Certification>>(certifications, HttpStatus.OK);
    }

    @RequestMapping(value= "{id}",method = RequestMethod.GET)
    public ResponseEntity<Certification> get(@PathVariable("id") int id){
        logger.info("obteniendo certificacion");
        Certification certification = certificationService.findCertificationById(id);

        if(certification == null){
            return new ResponseEntity<Certification>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Certification>(certification, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> store(@RequestBody Certification certification, UriComponentsBuilder ucBuilder) {
        logger.info("certificacion: {}",certification);

        Certification newCertification = certificationService.findCertificationByCode(certification.getCertificationCode());
        if(newCertification != null){
            logger.info("Ya existe esta certificacion con codigo {}",certification.getCertificationCode());
            return new ResponseEntity<>(new CustomErrorType("La certificacion ya existe"), HttpStatus.CONFLICT);
        }
        certificationService.storeCertification(certification);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/certifications/{id}").buildAndExpand(certification.getCertificationId()).toUri());
        logger.info("headers {}",headers);
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }

   @RequestMapping(value = "/{code}", method = RequestMethod.PUT)
   public ResponseEntity actualizar(@PathVariable("code") String code, @RequestBody Certification certification) {
       Certification currentCertification = certificationService.findCertificationByCode(code);

       if (currentCertification == null) {
           return new ResponseEntity(new CustomErrorType("La certificacion que desea actualizar no existe"), HttpStatus.NOT_FOUND);
       }

       currentCertification.setCertificationTitle(certification.getCertificationTitle());

       certificationService.updateCertification(currentCertification);

       return new ResponseEntity<Certification>(currentCertification, HttpStatus.OK);
    }



}
