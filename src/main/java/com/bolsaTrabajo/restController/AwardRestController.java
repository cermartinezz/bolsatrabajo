package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.postulantInfo.Award;
import com.bolsaTrabajo.service.AwardService;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by or.merino on 22/05/2017.
 */
@RestController
@RequestMapping("/award")
public class AwardRestController {
    @Autowired
    AwardService awardService;

    @Autowired
    PostulantService postulantService;

    // -------------------Crear Logro-------------------------------------------
    @RequestMapping(method = RequestMethod.POST)
    public RedirectView store(Award award, RedirectAttributes attributes){
        try{
            award.setPostulant(postulantService.findByUsername(Auth.auth().getName()));
            awardService.save(award);
        }catch (Exception e){
            attributes.addFlashAttribute("message","No se pudo guardar el logro con nombre "+award.getNombre());
            return new RedirectView("/roles/crear");
        }

        attributes.addFlashAttribute("messageSuccess","El logro se añadio correctamente");
        return new RedirectView("/postulante/"+Auth.auth().getName()+"/perfil#logros-premios-etc");
    }

    // -------------------Actualizar Logro-------------------------------------------
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public RedirectView update(@PathVariable("id") long id, Award award, RedirectAttributes attributes) {
        Award newAward = awardService.findById(id);

        if (newAward==null) {
            attributes.addFlashAttribute("message","No se pudo actualizar el logro");
            return new RedirectView("/awards/"+id);
        }
        newAward.setNombre(award.getNombre());
        newAward.setLugar(award.getLugar());
        newAward.setFecha(award.getFecha());
        awardService.save(newAward);
        attributes.addFlashAttribute("messageSuccess","El logro se actualizo correctamente");
        return new RedirectView("/postulante/"+Auth.auth().getName()+"/perfil#logros-premios-etc");
    }

    // -------------------Eliminar Logro-------------------------------------------
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable("id") long id,RedirectAttributes attributes) {
        awardService.deleteById(id);
        attributes.addFlashAttribute("messageSuccess","El logro se elimino correctamente");
        return new RedirectView("/postulante/"+Auth.auth().getName()+"/perfil#logros-premios-etc");
    }
}
