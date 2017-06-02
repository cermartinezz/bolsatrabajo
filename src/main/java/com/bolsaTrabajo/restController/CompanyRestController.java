package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.model.Role;
import com.bolsaTrabajo.service.CompanyService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 * Created by mendez on 05-06-17.
 */
@RestController
@RequestMapping("/company")
public class CompanyRestController {

    //private HttpHeaders headers;
    @Autowired
    private CompanyService companyService;


    @RequestMapping(method=RequestMethod.GET)
    public RedirectView store(Company company) {
        companyService.save(company);
        return new RedirectView("/company");
    }

    /* @GetMapping(value= "{user}")
      public ResponseEntity show(@PathVariable("user") String user){

          Company company = companyService.findByUsername(user);

          if(company == null){
              return new ResponseEntity(HttpStatus.NOT_FOUND);
          }

          return new ResponseEntity(company, HttpStatus.OK);
      }
  */
    /*
    @RequestMapping (value = "{id}, method=RequestMethod.PUT)
    public ResponseEntity update(@PathVariable String id, @Valid @RequestBody Company company, UriComponentsBuilder ucBuilder) {

        Company newcompany = companyService.findById(id);

        newcompany.setNombreC(company.getNombreC());
        newcompany.setRepreLegal(company.getRepreLegal());
        newcompany.setNitC(company.getNitC());
        newcompany.setTelefonoC(company.getTelefonoC());
        newcompany.setInformacionC(company.getInformacionC());

        companyService.updateCompany(newcompany);
        this.headers.set("message","Compañia Actualizada");
        this.headers.setLocation(ucBuilder.path("/company/{username}/profile-company").buildAndExpand(company.getUsername()).toUri());

        return new ResponseEntity(newcompany, this.headers, HttpStatus.OK);

    }
}
     /* if (newcompany==null) {
          attributes.addFlashAttribute("message","No se pudo actualizar el rol");
          return new RedirectView("/company/"+user);
      }
      companyService.save(newcompany);
      attributes.addFlashAttribute("messageSuccess","La empresa se actualizo correctamente");
      return new RedirectView("/company");
  }
    }
    */
    @RequestMapping(value = "{username}", method = RequestMethod.PUT)
    public RedirectView update(@PathVariable("username") String username, Company company, RedirectAttributes attributes) {
        Company newCompany=companyService.findByUsername(username);

        if (newCompany == null) {
            attributes.addFlashAttribute("message", "No se pudo actualizar la compañia");
            return new RedirectView("/company/" + company.getUsername()+"/editar");
        }

        newCompany.setNombreC(company.getNombreC());
        newCompany.setRepreLegal(company.getRepreLegal());
        newCompany.setNitC(company.getNitC());
        newCompany.setTelefonoC(company.getTelefonoC());
        newCompany.setInformacionC(company.getInformacionC());

        companyService.updateCompany(newCompany);
        String hola= newCompany.getUsername();
        attributes.addFlashAttribute("messageSuccess", "La compañia se actualizo correctamente");
        return new RedirectView("/company/" + company.getUsername()+"/profile");
    }
}


