package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.Award;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.service.AwardService;
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

@Controller
public class AwardController {
    @Autowired
    AwardService awardService;

    @Autowired
    UserService userService;

    @ModelAttribute("usr")
    public User globalUser(Model model) {
        User u = userService.findByUsername(Auth.auth().getName());
        return u;
    }

    @RequestMapping(value = "/logros/crear", method = RequestMethod.GET)
    public String create(ModelMap model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("award",new Award());
        return "Postulante/logros/crear";
    }

    @RequestMapping(value = "/logros/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable String id){
        ModelAndView model = new ModelAndView();

        Award award = awardService.findById(Long.parseLong(id));
        model.addObject("user", Auth.auth());
        model.addObject("award",award);
        model.setViewName("Postulante/logros/editar");
        return model;
    }
}
