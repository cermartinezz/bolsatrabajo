package com.bolsaTrabajo.controller;


import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.service.SecurityService;
import com.bolsaTrabajo.service.UserService;
import com.bolsaTrabajo.util.Auth;
import com.bolsaTrabajo.validator.PostulantValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrarController {

    public static final Logger log = LoggerFactory.getLogger(RegistrarController.class);

    @Autowired
    private UserService userService;


    @Autowired
    private SecurityService securityService;


    @Autowired
    private PostulantValidator postulantValidator;

    @RequestMapping(value = "/registrar/postulante", method = RequestMethod.GET)
    public String registration(Model model) {
        if(!Auth.auth().getPrincipal().equals("anonymousUser")){
            return "redirect:/";
        }else{
            model.addAttribute("userForm", new Postulant());
            model.addAttribute("user", Auth.auth());
            return "registrar/postulante";
        }
    }

    @RequestMapping(value = "/registrar/postulante", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") Postulant userForm, BindingResult bindingResult, Model model) {

        postulantValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("postuante {}", bindingResult.getAllErrors());
            return "registrar/postulante";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    @RequestMapping("/registro")
    public String registro(Model model){
        model.addAttribute("user", Auth.auth());

        return "registrar/menu";
    }
}
