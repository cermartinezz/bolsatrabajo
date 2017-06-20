package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.catalog.Area;
import com.bolsaTrabajo.service.AreaService;
import com.bolsaTrabajo.util.Auth;
import com.bolsaTrabajo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/area")
public class AreaRestController {

    @Autowired
    AreaService areaService;

    // -------------------Crear Area-------------------------------------------
    @RequestMapping(method = RequestMethod.POST)
    public RedirectView store(Area area, RedirectAttributes attributes){
        try{
            areaService.spSaveArea(area);
        }catch(Exception e){
            // Esto es porq regresa ora-20001 mensaje importante ora-20001 "spring....."
            // solo deja el mensaje importante
            attributes.addFlashAttribute("message",StringUtils.clearMessage(e.getCause().getCause().getMessage()));
            return new RedirectView("/examenes/areas/crear");
        }
        attributes.addFlashAttribute("messageSuccess","El area se añadio correctamente");
        return new RedirectView("/examenes/areas");
    }

    // -------------------Actualizar Area-------------------------------------------
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public RedirectView update(@PathVariable("id") long id, Area area, RedirectAttributes attributes) {
        Area newArea = areaService.findById(id);

        if (newArea==null) {
            attributes.addFlashAttribute("message","No se pudo actualizar el Area");
            return new RedirectView("/examenes/areas/"+id);
        }

        try{
            newArea.setNombre(area.getNombre());
            areaService.spSaveArea(area);
        }catch(Exception e){
            // Esto es porq regresa ora-20001 mensaje importante ora-20001 "spring....."
            // solo deja el mensaje importante
            attributes.addFlashAttribute("message",StringUtils.clearMessage(e.getCause().getCause().getMessage()));
            return new RedirectView("/examenes/areas/"+id);
        }
        attributes.addFlashAttribute("messageSuccess","El Area se actualizo correctamente");
        return new RedirectView("/examenes/areas");
    }

    // -------------------Eliminar Area-------------------------------------------
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable("id") long id,RedirectAttributes attributes) {
        areaService.deleteById(id);
        attributes.addFlashAttribute("messageSuccess","El Area se elimino correctamente");
        return new RedirectView("/examenes/areas");
    }
}
