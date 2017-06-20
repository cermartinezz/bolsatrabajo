package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.catalog.SubArea;
import com.bolsaTrabajo.service.SubAreaService;
import com.bolsaTrabajo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/subarea")
public class SubAreaRestController {
    @Autowired
    SubAreaService subAreaService;

    // -------------------Crear SubArea-------------------------------------------
    @RequestMapping(method = RequestMethod.POST)
    public RedirectView store(SubArea subArea, RedirectAttributes attributes){
        try{
            int i = 0;
            subAreaService.spSaveSubArea(subArea);
            i=2;
        }catch(Exception e){
            // Esto es porq regresa ora-20001 mensaje importante ora-20001 "spring....."
            // solo deja el mensaje importante
            attributes.addFlashAttribute("message", StringUtils.clearMessage(e.getCause().getCause().getMessage()));
            return new RedirectView("/examenes/subareas/crear");
        }
        attributes.addFlashAttribute("messageSuccess","El area se añadio correctamente");
        return new RedirectView("/examenes/subareas");
    }

    // -------------------Actualizar Pregunta-------------------------------------------
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public RedirectView update(@PathVariable("id") long id, SubArea subArea, RedirectAttributes attributes) {

        if (subArea==null) {
            attributes.addFlashAttribute("message","No se pudo actualizar el Area");
            return new RedirectView("/examenes/subareas/"+id);
        }
        try{
            subAreaService.spSaveSubArea(subArea);
        }catch(Exception e){
            // Esto es porq regresa ora-20001 mensaje importante ora-20001 "spring....."
            // solo deja el mensaje importante
            attributes.addFlashAttribute("message",StringUtils.clearMessage(e.getCause().getCause().getMessage()));
            return new RedirectView("/examenes/subareas/"+id);
        }
        attributes.addFlashAttribute("messageSuccess","El Area se actualizo correctamente");
        return new RedirectView("/examenes/subareas");
    }

    // -------------------Eliminar Area-------------------------------------------
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable("id") long id,RedirectAttributes attributes) {
        subAreaService.deleteById(id);
        attributes.addFlashAttribute("messageSuccess","El SubArea se elimino correctamente");
        return new RedirectView("/examenes/subareas");
    }
}
