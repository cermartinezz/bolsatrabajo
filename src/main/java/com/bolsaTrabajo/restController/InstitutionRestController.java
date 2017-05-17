package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Certification;
import com.bolsaTrabajo.model.Institution;
import com.bolsaTrabajo.service.CertificationService;
import com.bolsaTrabajo.service.InstitutionService;
import com.bolsaTrabajo.util.CustomErrorType;
import com.bolsaTrabajo.util.CustomSuccessType;
import com.bolsaTrabajo.validator.InstitutionValidator;
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
@RequestMapping("/api/instituciones")
public class InstitutionRestController {

    public static final Logger logger = LoggerFactory.getLogger(CertificationRestController.class);

    public InstitutionRestController() {
        this.headers = new HttpHeaders();
    }

    private HttpHeaders headers;

    @Autowired
    private InstitutionValidator institutionValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(institutionValidator);
    }

    @Autowired
    public InstitutionService institutionService;

    @GetMapping
    public ResponseEntity<List<Institution>> index() {
        List<Institution> institution = institutionService.getAllInstitutions();

        if (institution == null || institution.isEmpty()) {
            return new ResponseEntity<List<Institution>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Institution>>(institution, HttpStatus.OK);
    }

    @RequestMapping(value= "{code}",method = RequestMethod.GET)
    public ResponseEntity<Institution> show(@PathVariable("code") String code){

        Institution institution = institutionService.findInstitutionByCode(code);

        if(institution == null){
            headers.set("message","No se encontraron registros");
            return new ResponseEntity<Institution>(headers,HttpStatus.NOT_FOUND);
        }
        headers.set("message","Registros Encontrados");
        return new ResponseEntity<Institution>(institution, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody Institution institution, UriComponentsBuilder ucBuilder) {

        institutionService.save(institution);

        this.headers.setLocation(ucBuilder.path("/instituciones").buildAndExpand(institution.getInstitutionCode()).toUri());


        return new ResponseEntity<String>(this.headers, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/{code}", method = RequestMethod.PUT)
    public ResponseEntity actualizar(@PathVariable("code") String code,
                                     @Valid @RequestBody Institution institution,
                                     UriComponentsBuilder uriBuilder) {

        Institution currentInstitution = institutionService.findInstitutionByCode(code);

        currentInstitution.setInstitutionName(institution.getInstitutionName());

        institutionService.update(currentInstitution);

        this.headers.set("message","Registro Actualizado con exito");

        this.headers.setLocation(uriBuilder.path("/instituciones").build().toUri());

        return new ResponseEntity(this.headers, HttpStatus.OK);
    }



}
