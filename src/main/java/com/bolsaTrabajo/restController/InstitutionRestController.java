package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Certification;
import com.bolsaTrabajo.model.Institution;
import com.bolsaTrabajo.service.CertificationService;
import com.bolsaTrabajo.service.InstitutionService;
import com.bolsaTrabajo.util.CustomErrorType;
import com.bolsaTrabajo.util.CustomSuccessType;
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
@RequestMapping("/instituciones")
public class InstitutionRestController {

    public static final Logger logger = LoggerFactory.getLogger(CertificationRestController.class);

    @Autowired
    public InstitutionService institutionService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Institution>> getAll() {
        List<Institution> institution = institutionService.getAllInstitutions();

        if (institution == null || institution.isEmpty()) {
            return new ResponseEntity<List<Institution>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Institution>>(institution, HttpStatus.OK);
    }

    @RequestMapping(value= "{code}",method = RequestMethod.GET)
    public ResponseEntity<Institution> get(@PathVariable("code") String code){
        logger.info("obteniendo institucion");
        Institution institution = institutionService.findInstitutionByCode(code);

        if(institution == null){
            return new ResponseEntity<Institution>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Institution>(institution, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> store(@RequestBody Institution institution, UriComponentsBuilder ucBuilder) {
        logger.info("certificacion: {}",institution);

        Institution newCertification = institutionService.findInstitutionByCode(institution.getInstitutionCode());
        if(newCertification != null){
            logger.info("Ya existe esta certificacion con codigo {}",institution.getInstitutionCode());
            return new ResponseEntity<>(new CustomErrorType("La institucion ya existe"), HttpStatus.CONFLICT);
        }
        institutionService.save(institution);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/instituciones/{id}").buildAndExpand(institution.getInstitutionCode()).toUri());
        logger.info("headers {}",headers);
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/{code}", method = RequestMethod.PUT)
    public ResponseEntity actualizar(@PathVariable("code") String code, @RequestBody Institution institution) {
        Institution currentInstitution = institutionService.findInstitutionByCode(code);

        if (currentInstitution == null) {
            return new ResponseEntity(new CustomErrorType("La certificacion que desea actualizar no existe"), HttpStatus.NOT_FOUND);
        }

        currentInstitution.setInstitutionName(institution.getInstitutionName());

        institutionService.update(currentInstitution);

        return new ResponseEntity(new CustomSuccessType("La certificacion se creo con exito!"), HttpStatus.OK);
    }



}
