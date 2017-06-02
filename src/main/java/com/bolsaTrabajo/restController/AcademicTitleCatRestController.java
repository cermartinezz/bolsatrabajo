package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.catalog.AcademicTitleCat;
import com.bolsaTrabajo.service.AcademicTitleCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by keepercito on 05-21-17.
 */

@RestController
@RequestMapping("/api/cat/titles")
public class AcademicTitleCatRestController {

    @Autowired
    private AcademicTitleCatService academicTitleCatService;

    @PostMapping
    public RedirectView store(AcademicTitleCat academicTitleCat, RedirectAttributes attributes){

        AcademicTitleCat academicTitleCat1 = academicTitleCatService.getTitle(academicTitleCat.getTitulo());
        if (academicTitleCat1 != null){
            attributes.addFlashAttribute("message","Titulo "+ academicTitleCat1.getTitulo()+" ya existe");
            return new RedirectView("/cat/titles/crear");
        }

        academicTitleCatService.saveTitle(academicTitleCat);
        attributes.addFlashAttribute("message","Registro se guardo con exito");
        return new RedirectView("/cat/titles");
    }

    @PutMapping(value = "/update/{id}")
    public RedirectView update(AcademicTitleCat academicTitleCat, RedirectAttributes attributes){
        AcademicTitleCat t = academicTitleCatService.getTitle(academicTitleCat.getTitulo());
        if (t!=null){
            attributes.addFlashAttribute("message","Titulo "+ t.getTitulo()+" ya existe");
            return new RedirectView("/cat/titles/editar/"+ academicTitleCat.getId());
        }
        t = academicTitleCatService.getTitle(academicTitleCat.getId());
        t.setTitulo(academicTitleCat.getTitulo());
        academicTitleCatService.saveTitle(t);
        attributes.addFlashAttribute("message","Registro modificado con exito");
        return new RedirectView("/cat/titles");
    }

    @DeleteMapping(value = "/delete/{id}")
    public RedirectView delete(AcademicTitleCat academicTitleCat, RedirectAttributes attributes){
        academicTitleCatService.deleteTitle(academicTitleCat);
        attributes.addFlashAttribute("message","Registro se elimino con exito");
        return new RedirectView("/cat/titles");
    }
}
