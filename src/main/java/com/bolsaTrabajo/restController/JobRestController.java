package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.model.Job;
import com.bolsaTrabajo.service.CompanyService;
import com.bolsaTrabajo.service.JobService;
import com.bolsaTrabajo.util.Auth;
import com.bolsaTrabajo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@RequestMapping("/job")
public class JobRestController {
    @Autowired
    private JobService jobService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping(method= RequestMethod.POST)
    public RedirectView store(Job job, RedirectAttributes attributes) {
        String nombre=job.getNombreJ();
        String codigo=StringUtil.toSlug(nombre);

        Company company=companyService.findByUsername(Auth.auth().getName());
        job.setCompany(company);
        job.setCodJ(codigo);
        jobService.save(job);
        return new RedirectView("/puesto/"+job.getId()+"/perfil");
    }
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public RedirectView update(@PathVariable("id") Long id, Job job, RedirectAttributes attributes) {
        Job newJob= jobService.findById(id);

        if (newJob == null) {
            attributes.addFlashAttribute("message", "No se pudo actualizar el puesto");
            return new RedirectView("/puesto/" + job.getId()+"/editar");
        }

        newJob.setNombreJ(job.getNombreJ());
        newJob.setSalarioJ(job.getSalarioJ());
        newJob.setDescripcionJ(job.getDescripcionJ());

        jobService.updateJob(newJob);
        //String hola= newJob.getId();
        attributes.addFlashAttribute("messageSuccess", "El puesto se actualizo correctamente");
        return new RedirectView("/puesto/"+job.getId()+"/perfil");
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable("id") Long id, RedirectAttributes attributes) {
        jobService.deleteById(id);
        attributes.addFlashAttribute("messageSuccess", "El puesto ha sido eliminado correctamente");
        return new RedirectView("/puesto/lista");
    }

}
