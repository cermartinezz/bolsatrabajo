package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.catalog.JobCat;
import com.bolsaTrabajo.service.JobCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by keepercito on 05-01-17.
 */

@RestController
@RequestMapping("/api/cargos")
public class JobCatRestController {

    @Autowired
    private JobCatService jobCatService;

    @PostMapping
    public RedirectView store(JobCat jobCat, RedirectAttributes attributes){

        JobCat jobCat1 = jobCatService.getJob(jobCat.getPuesto());
        if (jobCat1 != null){
            attributes.addFlashAttribute("message","Puesto "+ jobCat1.getPuesto()+" ya existe");
            return new RedirectView("/cat/cargos/crear");
        }

        jobCatService.saveJob(jobCat);
        attributes.addFlashAttribute("message","Registro se guardo con exito");
        return new RedirectView("/cat/cargos");
    }

    @PutMapping(value = "/update/{id}")
    public RedirectView update(JobCat jobCat, RedirectAttributes attributes){
        JobCat e = jobCatService.getJob(jobCat.getPuesto());
        if (e!=null){
            attributes.addFlashAttribute("message","Puesto "+ e.getPuesto()+" ya existe");
            return new RedirectView("/cat/cargos/editar/"+ jobCat.getId());
        }
        e = jobCatService.getJob(jobCat.getId());
        e.setPuesto(jobCat.getPuesto());
        jobCatService.saveJob(e);
        attributes.addFlashAttribute("message","Registro modificado con exito");
        return new RedirectView("/cat/cargos");
    }

    @DeleteMapping(value = "/delete/{id}")
    public RedirectView delete(JobCat jobCat, RedirectAttributes attributes){
        jobCatService.deleteJob(jobCat);
        attributes.addFlashAttribute("message","Registro se elimino con exito");
        return new RedirectView("/cat/cargos");
    }
}
