package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.util.Auth;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(ModelMap modelMap){

        modelMap.addAttribute("user", Auth.auth());
        return "home";
    }

    @RequestMapping("/registro")
    public String registro(){
        return "registrar/menu";
    }

}
