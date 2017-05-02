package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.service.SecurityService;
import com.bolsaTrabajo.service.UserService;
import com.bolsaTrabajo.util.Auth;
import com.bolsaTrabajo.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {

        if(!Auth.auth().getPrincipal().equals("anonymousUser")){
            return "/";
        }else{
            User u = userService.findByUsername(Auth.auth().getName());
            model.addAttribute("user",Auth.auth());
            model.addAttribute("u",u);
            if (error != null)
                model.addAttribute("error", "Your username and password is invalid.");

            if (logout != null)
                model.addAttribute("message", "You have been logged out successfully.");

            return "login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = Auth.auth();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request,response,auth);
        }
        return "redirect:/login?logout";
    }
}
