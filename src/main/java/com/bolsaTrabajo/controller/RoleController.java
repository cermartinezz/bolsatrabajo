package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.Permission;
import com.bolsaTrabajo.model.Role;
import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.service.PermissionService;
import com.bolsaTrabajo.service.RoleService;
import com.bolsaTrabajo.service.UserService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    PermissionService permissionService;

    @ModelAttribute("usr")
    public User globalUser(Model model) {
        User u = userService.findByUsername(Auth.auth().getName());
        return u;
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/roles/index";
    }

    @RequestMapping(value = "/roles/crear", method = RequestMethod.GET)
    public String create(ModelMap model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("role",new Role());
        return "admin/roles/crear";
    }

    @RequestMapping(value = "/roles/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable String id){
        ModelAndView model = new ModelAndView();
        Role role = roleService.findById(Long.parseLong(id));

        //Estableciendo lista de permisos que no son parte del rol
        HashSet<Permission> permissionsCollection = new HashSet<>(permissionService.getAllPermissions());

        model.addObject("permisos",permissionsCollection);
        model.addObject("permiso",new Permission());
        model.addObject("user", Auth.auth());
        model.addObject("role",role);
        model.setViewName("admin/roles/editar");
        return model;
    }
}
