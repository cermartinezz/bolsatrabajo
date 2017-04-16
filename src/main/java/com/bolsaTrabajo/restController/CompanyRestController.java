package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.service.CompanyService;
import com.bolsaTrabajo.util.Auth;
import com.bolsaTrabajo.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by enan0 on 14/4/2017.
 */
@RestController
@RequestMapping("/company")
public class CompanyRestController {
    public static final Logger logger = LoggerFactory.getLogger(CompanyRestController.class);

    @Autowired
    private CompanyService companyService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> store(Company company, UriComponentsBuilder ucBuilder){
        logger.info("company: {}",company);

        Company company1 = companyService.getCompany(company.getCompanyName());
        if (company1 != null){
            logger.info("Ya existe empresa con nombre {}",company.getCompanyName());
            return new ResponseEntity<>(new CustomErrorType("La compañia ya existe"), HttpStatus.CONFLICT);
        }

        companyService.saveCompany(company);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/empresas/{id}").buildAndExpand(company.getId()).toUri());
        logger.info("headers {}", headers);
        return new ResponseEntity<String>(headers,HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Company>> getAll() {
        List<Company> certifications = companyService.getAllCompanies();

        if (certifications == null || certifications.isEmpty()) {
            return new ResponseEntity<List<Company>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Company>>(certifications, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public RedirectView update(@PathVariable("id") long id, Company company){
        Company e = companyService.getCompany(company.getId());
        e.setCompanyName(company.getCompanyName());
        companyService.saveCompany(e);
        return new RedirectView("/cat/empresas");
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable("id") long id, Company company){
        /*Company e = companyService.getCompany(company.getId());
        e.setCompanyName(company.getCompanyName());*/
        companyService.deleteCompany(company);
        return new RedirectView("/cat/empresas");
    }
}
