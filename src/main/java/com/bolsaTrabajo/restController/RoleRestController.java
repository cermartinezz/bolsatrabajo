package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Role;
import com.bolsaTrabajo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;



@RestController
@RequestMapping("/role")
public class RoleRestController {

    @Autowired
    private RoleService roleService;


    // -------------------Crear Role-------------------------------------------
    @RequestMapping(method = RequestMethod.POST)
    public RedirectView store(Role role, RedirectAttributes attributes){
        Role newrole = roleService.findByName(role.getName());
        if (newrole != null){
            attributes.addFlashAttribute("message","Ya existe un rol con nombre "+role.getName());
            return new RedirectView("/roles/crear");
        }
        roleService.save(role);

        attributes.addFlashAttribute("messageSuccess","El rol se creo correctamente");
        return new RedirectView("/roles");
    }

    // -------------------Actualizar Role-------------------------------------------
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public RedirectView update(@PathVariable("id") long id, Role role, RedirectAttributes attributes) {
        Role newRole = roleService.findById(id);

        if (newRole==null) {
            attributes.addFlashAttribute("message","No se pudo actualizar el rol");
            return new RedirectView("/roles/"+id);
        }

        newRole = role;
        roleService.save(newRole);
        attributes.addFlashAttribute("messageSuccess","El rol se actualizo correctamente");
        return new RedirectView("/roles");
    }

    // -------------------Eliminar Role-------------------------------------------
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable("id") long id,RedirectAttributes attributes) {
        roleService.delete(id);
        attributes.addFlashAttribute("messageSuccess","El rol se elimino correctamente");
        return new RedirectView("/roles");
    }
}
