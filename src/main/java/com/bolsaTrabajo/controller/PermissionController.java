package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.Permission;
import com.bolsaTrabajo.service.PermissionService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PermissionController {
    
    @Autowired
    PermissionService permissionService;

    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("permissions", permissionService.getAllPermissions());
        return "admin/permissions/index";
    }

    @RequestMapping(value = "/permissions/crear", method = RequestMethod.GET)
    public String create(ModelMap model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("permission",new Permission());
        return "admin/permissions/crear";
    }

    @RequestMapping(value = "/permissions/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable long id){
        ModelAndView model = new ModelAndView();
        Permission permission = permissionService.findById(id);
        model.addObject("permission",permission);
        model.setViewName("admin/permissions/editar");
        return model;
    }
}
