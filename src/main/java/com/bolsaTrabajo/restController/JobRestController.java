package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.model.Job;
import com.bolsaTrabajo.model.catalog.Department;
import com.bolsaTrabajo.service.CompanyService;
import com.bolsaTrabajo.service.DepartmentService;
import com.bolsaTrabajo.service.JobService;
import com.bolsaTrabajo.util.Auth;
import com.bolsaTrabajo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@RestController
@RequestMapping("/job")
public class JobRestController {

    private HttpHeaders headers;


    @Autowired
    private JobService jobService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private DepartmentService departmentService;

    public JobRestController() {
        this.headers = new HttpHeaders();
    }

    @RequestMapping(method= RequestMethod.POST)
    public RedirectView store(Job job, RedirectAttributes attributes) {
        Company company=companyService.findByUsername(Auth.auth().getName());
        String nombre=job.getNombreJ() + "-" + company.getId();
        String codigo=StringUtil.toSlug(nombre);
        String category = job.getCategory();
        Department department = job.getDepartment();
        String nomDept = department.getNombre();
        Department depto = departmentService.findByNombre(nomDept);
        Job byCodJ = jobService.findByCodJ(codigo);
        if(!(byCodJ == null)){
            return new RedirectView("/puesto/lista");
        }
        job.setCompany(company);
        job.setCodJ(codigo);
        job.setCategory(category);
        job.setDepartment(depto);
        jobService.save(job);
        return new RedirectView("/puesto/lista");
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
        newJob.setCategory(job.getCategory());

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

    @GetMapping(value="/list")
    public ResponseEntity<List<Job>> getAllJobs(){
        List<Job> jobs = jobService.getAllJobs();
        if (jobs == null||jobs.isEmpty()){
            return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
    }

    @GetMapping(value="/list/cat/{category}")
    public ResponseEntity<List<Job>> getAllJobsByCategory(@PathVariable String category){
        List<Job> jobs = jobService.findByCategory(category);
        if (jobs == null||jobs.isEmpty()){
            this.headers.set("message", "No se encontraron trabajos");
            return new ResponseEntity<List<Job>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
    }

    @GetMapping(value="/list/dept/{location}")
    public ResponseEntity<List<Job>> getAllJobsByDepartment(@PathVariable String location){
        Department dept = departmentService.findByCodigo(location);
        List<Job> jobs = jobService.findByDepartment(dept);
        if (jobs == null||jobs.isEmpty()){
            this.headers.set("message", "No se encontraron trabajos");
            return new ResponseEntity<List<Job>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
    }

    @GetMapping(value="/list/cat/{category}/dept/{location}")
    public ResponseEntity<List<Job>> getAllJobsByDepartmentandCategory(@PathVariable String category,@PathVariable String location){
        Department dept = departmentService.findByCodigo(location);
        List<Job> jobs = jobService.findByCategoryAndDepartment(category,dept);
        if (jobs == null||jobs.isEmpty()){
            this.headers.set("message", "No se encontraron trabajos");
            return new ResponseEntity<List<Job>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
    }

}
