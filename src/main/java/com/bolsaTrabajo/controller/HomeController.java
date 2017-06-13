package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.service.UserService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String home(ModelMap modelMap){
        /*User u = userService.findByUsername(Auth.auth().getName());
        modelMap.addAttribute("u",u);*/
        modelMap.addAttribute("user", Auth.auth());
        return "home";
    }



    @RequestMapping("/admin/menu")
    public String adminMenu(){
        return "admin/menu_admin";
    }
}
