package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.Role;
import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.service.UserService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @ModelAttribute("usr")
    public User globalUser(Model model) {
        User u = userService.findByUsername(Auth.auth().getName());
        if(u == null){
            u = new User();
            Set<Role> roles = new HashSet<>();
            Role role = new Role();
            role.setName("INVITADO");
            roles.add(role);
            u.setRoles(roles);
            return u;
        }
        return u;
    }

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
