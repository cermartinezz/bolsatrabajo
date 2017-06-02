package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Permission;
import com.bolsaTrabajo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/permission")
public class PermissionRestController {
    
    @Autowired
    PermissionService permissionService;

    // -------------------Crear Permiso-------------------------------------------
    @RequestMapping(method = RequestMethod.POST)
    public RedirectView store(Permission permission, RedirectAttributes attributes){
        Permission newPermission = permissionService.findByName(permission.getName());
        if (newPermission != null){
            attributes.addFlashAttribute("message","Ya existe un permiso con nombre "+permission.getName());
            return new RedirectView("/permissions/crear");
        }
        permissionService.save(permission);

        attributes.addFlashAttribute("messageSuccess","El permiso se creo correctamente");
        return new RedirectView("/permissions");
    }

    // -------------------Actualizar Permiso-------------------------------------------
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public RedirectView update(@PathVariable("id") long id, Permission permission, RedirectAttributes attributes) {
        Permission newPermission = permissionService.findById(id);

        if (newPermission==null) {
            attributes.addFlashAttribute("message","No se pudo actualizar el permiso");
            return new RedirectView("/permissions/"+id);
        }

        newPermission = permission;
        permissionService.save(newPermission);
        attributes.addFlashAttribute("messageSuccess","El permiso se actualizo correctamente");
        return new RedirectView("/permissions");
    }

    // -------------------Eliminar Permiso-------------------------------------------
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable("id") long id,RedirectAttributes attributes) {
        permissionService.delete(id);
        attributes.addFlashAttribute("messageSuccess","El permiso se elimino correctamente");
        return new RedirectView("/permissions");
    }
}
