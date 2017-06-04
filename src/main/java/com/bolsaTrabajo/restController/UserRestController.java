package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.service.CompanyService;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.service.UserService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by or.merino on 02/06/2017.
 */
@RestController
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    UserService userService;
    @Autowired
    PostulantService postulantService;
    @Autowired
    CompanyService companyService;

    // -------------------Eliminar Usuario-------------------------------------------
    @RequestMapping(value = "{username}",method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable("username") String username, Model model, RedirectAttributes attributes) {

        Postulant postulant = postulantService.findByUsername(username);
        if (postulant==null){
            Company company = companyService.findByUsername(username);
            if (company==null){
                attributes.addFlashAttribute("message","No se pudo encontrar el tipo de Usuario");
                return new RedirectView("/usuarios");
            }
            //Comprobar que no sea el usuario actual o logueado
            if (company.getUsername().equals(Auth.auth().getName())){
                attributes.addFlashAttribute("message","No es posible eliminar su propio usuario.");
                return new RedirectView("/usuarios");
            }
            companyService.delete(company);
            model.addAttribute("user", Auth.auth());
            return new RedirectView("/usuarios");
        }

        if (postulant.getUsername().equals(Auth.auth().getName())){
            attributes.addFlashAttribute("message","No es posible eliminar su propio usuario.");
            return new RedirectView("/usuarios");
        }
        postulantService.delete(postulant);
        attributes.addFlashAttribute("messageSuccess","El usuario se elimino correctamente");
        return new RedirectView("/usuarios");
    }
}
