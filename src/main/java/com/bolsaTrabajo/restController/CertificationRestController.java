package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Certification;
import com.bolsaTrabajo.model.Institution;
import com.bolsaTrabajo.service.CertificationService;
import com.bolsaTrabajo.service.InstitutionService;
import com.bolsaTrabajo.util.Auth;
import com.bolsaTrabajo.util.CustomErrorType;
import com.bolsaTrabajo.validator.CertificationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        logger.info("certification {}",certification);
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

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(ucBuilder.path("/certifications/{id}").buildAndExpand(certification.getCertificationId()).toUri());

        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }

   @PutMapping(value = "/{code}")
   public ResponseEntity update(@PathVariable("code") String code, @Valid @RequestBody Certification certification) {

        Certification currentCertification = certificationService.findCertificationByCode(code);

       currentCertification.setCertificationTitle(certification.getCertificationTitle());

       certificationService.updateCertification(currentCertification);

       return new ResponseEntity(currentCertification, HttpStatus.OK);
    }



}
