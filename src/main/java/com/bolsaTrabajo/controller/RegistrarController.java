package com.bolsaTrabajo.controller;


import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.Role;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.service.RoleService;
import com.bolsaTrabajo.service.SecurityService;
import com.bolsaTrabajo.service.UserService;
import com.bolsaTrabajo.util.Auth;
import com.bolsaTrabajo.validator.CompanyValidator;
import com.bolsaTrabajo.validator.PostulantValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;

@Controller
public class RegistrarController {

    public static final Logger log = LoggerFactory.getLogger(RegistrarController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PostulantService postulantService;


    @Autowired
    private SecurityService securityService;

    @Autowired
    private RoleService roleRepository;

    @Autowired
    private PostulantValidator postulantValidator;

    @Autowired
    private CompanyValidator companyValidator;

    @RequestMapping(value = "/registrar/postulante", method = RequestMethod.GET)
    public String registration(Model model) {
        if(Auth.check()){
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

            model.addAttribute("user", Auth.auth());

            return "registrar/postulante";
        }

        userForm.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));

        userForm.setActive(1);

        HashSet<Role> roleCollection = new HashSet<>();

        Role ROLE = roleRepository.findByName("POSTULANTE");

        roleCollection.add(ROLE);

        userForm.setRoles(roleCollection);

        postulantService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    @RequestMapping(value = "/registrar/company", method = RequestMethod.GET)
    public String registrationC(Model model) {
        if(!Auth.auth().getPrincipal().equals("anonymousUser")){
            return "redirect:/";
        }else{
            model.addAttribute("userForm", new Company());
            model.addAttribute("user", Auth.auth());
            return "registrar/company";
        }
    }
    @RequestMapping(value = "/registrar/company", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") Company userForm, BindingResult bindingResult, Model model) {

        companyValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("company {}", bindingResult.getAllErrors());
            model.addAttribute("userForm", new Company());
            model.addAttribute("user", Auth.auth());
            return "registrar/company";
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
