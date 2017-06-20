package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.catalog.JobCat;
import com.bolsaTrabajo.service.JobCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * Created by keepercito on 05-01-17.
 */

@RestController
@RequestMapping("/api/cargos")
public class JobCatRestController {

    @Autowired
    private JobCatService jobCatService;

    private HttpHeaders httpHeaders;

    public JobCatRestController() {
        this.httpHeaders = new HttpHeaders();
    }



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

    @GetMapping
    public ResponseEntity<List<JobCat>> getAll(){
        List<JobCat> jobs = jobCatService.getAllJobs();

        if(jobs.equals(null) || jobs.size() == 0){
            this.httpHeaders.set("message","No se encontraro items");
            return new ResponseEntity<List<JobCat>>(this.httpHeaders, HttpStatus.NOT_FOUND);
        }

        this.httpHeaders.set("message","Lista de elementos llena");
        return new ResponseEntity<List<JobCat>>(jobs,this.httpHeaders, HttpStatus.OK);
    }
}
