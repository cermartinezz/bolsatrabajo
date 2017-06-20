package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.model.Job;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.Department;
import com.bolsaTrabajo.model.jobInfo.Candidate;
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
import java.util.Set;


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
        Job job1 = jobService.findByProfile(job.getJobProfile());
        if(! (job1 == null) ){
            attributes.addFlashAttribute("message","Ya existe un puesto con este perfil, no puede ser asociado");
            return new RedirectView("/puesto/crear");
        }
        Company company=companyService.findByUsername(Auth.auth().getName());
        String nombre=job.getNombreJ() + "-" + company.getId();
        String codigo=StringUtil.toSlug(nombre);
        String category = job.getCategory();
        Department department = job.getDepartment();
        String nomDept = department.getNombre();
        Department depto = departmentService.findByNombre(nomDept);
        Job byCodJ = jobService.findByCodJ(codigo);
        if(!(byCodJ == null)){
            attributes.addFlashAttribute("message","Ya existe un puesto con este perfil, no puede ser asociado");
            return new RedirectView("/puesto/crear");
        }
        job.setCompany(company);
        job.setCodJ(codigo);
        job.setCategory(category);
        job.setDepartment(depto);
        jobService.save(job);
        attributes.addFlashAttribute("messageSuccess","Se creo un nuevo puesto de trabajo");
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

    @GetMapping(value="/{id}/ver")
    public ResponseEntity show(@PathVariable Long id){
        Job job = jobService.findById(id);

        if(job == null){
            headers.set("message","No se encontraron registros");

            return new ResponseEntity(headers, HttpStatus.NOT_FOUND);
        }
        headers.set("message","Registros Encontrados");

        return new ResponseEntity(job,headers,HttpStatus.OK);
    }

    @GetMapping(value="/{id}/candidates")
    public ResponseEntity showCandidates(@PathVariable Long id){
        Job job = jobService.findById(id);


        if(job == null){
            headers.set("message","No se encontraron registros");

            return new ResponseEntity(headers, HttpStatus.NOT_FOUND);
        }
        headers.set("message","Registros Encontrados");

        return new ResponseEntity(job,headers,HttpStatus.OK);
    }

    @GetMapping("/candidatos/puesto/{job_id}/lenguaje/{code}")
    public ResponseEntity filtrarPorIdioma(@PathVariable Integer job_id, @PathVariable String code){

        Job job = jobService.findById(job_id);
        Set<Candidate> candidates = job.getCandidates();

        List<Postulant> postulants = new Candidate().extraerCandidatosPorLenguaje(candidates, code);

        if(postulants.size() <= 0){
            this.headers.set("message","no se encontraron candidatos");
            return new ResponseEntity(this.headers,HttpStatus.NOT_FOUND);
        }

        this.headers.set("message", "Se encontraron: "+ postulants.size() + " aspirantes");

        return new ResponseEntity(postulants,this.headers,HttpStatus.OK);
    }

    @GetMapping("/candidatos/puesto/{job_id}/habilidad/{id}")
    public ResponseEntity filtrarPorHabilidad(@PathVariable Integer job_id, @PathVariable Integer id){

        Job job = jobService.findById(job_id);
        Set<Candidate> candidates = job.getCandidates();

        Set<Postulant> postulants = new Candidate().extraerCandidatosPorHabilidad(candidates, id);

        if(postulants.size() <= 0){
            this.headers.set("message","no se encontraron candidatos");
            return new ResponseEntity(this.headers,HttpStatus.NOT_FOUND);
        }

        this.headers.set("message", "Se encontraron: "+ postulants.size() + " aspirantes");

        return new ResponseEntity(postulants,this.headers,HttpStatus.OK);
    }

    @GetMapping("/candidatos/puesto/{job_id}/educacion/{educacion}")
    public ResponseEntity filtrarPorEducacion(@PathVariable Integer job_id, @PathVariable String educacion){

        Job job = jobService.findById(job_id);
        Set<Candidate> candidates = job.getCandidates();

        Set<Postulant> postulants = new Candidate().extraerCandidatosPorEducacion(candidates, educacion);

        if(postulants.size() <= 0){
            this.headers.set("message","no se encontraron candidatos");
            return new ResponseEntity(this.headers,HttpStatus.NOT_FOUND);
        }

        this.headers.set("message", "Se encontraron: "+ postulants.size() + " aspirantes");

        return new ResponseEntity(postulants,this.headers,HttpStatus.OK);
    }

    @GetMapping("/candidatos/puesto/{job_id}/habilidad/{skill_id}/idioma/{lan_id}")
    public ResponseEntity filtrarPorHabilidadesEIdiomas(@PathVariable Integer job_id, @PathVariable Integer skill_id, @PathVariable String lan_id){

        Job job = jobService.findById(job_id);
        Set<Candidate> candidates = job.getCandidates();

        Set<Postulant> postulants = new Candidate().extraerCandidatosPorHabilidadEIdiomas(candidates, skill_id,lan_id);

        if(postulants.size() <= 0){
            this.headers.set("message","no se encontraron candidatos");
            return new ResponseEntity(this.headers,HttpStatus.NOT_FOUND);
        }

        this.headers.set("message", "Se encontraron: "+ postulants.size() + " aspirantes");

        return new ResponseEntity(postulants,this.headers,HttpStatus.OK);
    }

    @GetMapping("/candidatos/puesto/{job_id}/habilidad/{skill_id}/educacion/{education}")
    public ResponseEntity filtrarPorHabilidadYEducacion(@PathVariable Integer job_id, @PathVariable Integer skill_id, @PathVariable String education){

        Job job = jobService.findById(job_id);
        Set<Candidate> candidates = job.getCandidates();

        Set<Postulant> postulants = new Candidate().extraerCandidatosPorHabilidadYEducacion(candidates, skill_id,education);

        if(postulants.size() <= 0){
            this.headers.set("message","no se encontraron candidatos");
            return new ResponseEntity(this.headers,HttpStatus.NOT_FOUND);
        }

        this.headers.set("message", "Se encontraron: "+ postulants.size() + " aspirantes");

        return new ResponseEntity(postulants,this.headers,HttpStatus.OK);
    }

    @GetMapping("/candidatos/puesto/{job_id}/idioma/{lang_id}/educacion/{education}")
    public ResponseEntity filtrarPorIdiomaYEducacion(@PathVariable Integer job_id, @PathVariable String lang_id, @PathVariable String education){

        Job job = jobService.findById(job_id);
        Set<Candidate> candidates = job.getCandidates();

        Set<Postulant> postulants = new Candidate().extraerCandidatosPorIdiomaYEducacion(candidates, lang_id,education);

        if(postulants.size() <= 0){
            this.headers.set("message","no se encontraron candidatos");
            return new ResponseEntity(this.headers,HttpStatus.NOT_FOUND);
        }

        this.headers.set("message", "Se encontraron: "+ postulants.size() + " aspirantes");

        return new ResponseEntity(postulants,this.headers,HttpStatus.OK);
    }

    @GetMapping("/candidatos/puesto/{job_id}/idioma/{lang_id}/educacion/{education}/habilidad/{skill_id}")
    public ResponseEntity filtrarPorIdiomaYEducacionYHabilidad(@PathVariable Integer job_id, @PathVariable String lang_id, @PathVariable String education,@PathVariable Integer skill_id){

        Job job = jobService.findById(job_id);
        Set<Candidate> candidates = job.getCandidates();

        Set<Postulant> postulants = new Candidate().extraerCandidatosPorIdiomaYEducacionYHabildiad(candidates, lang_id,education,skill_id);

        if(postulants.size() <= 0){
            this.headers.set("message","no se encontraron candidatos");
            return new ResponseEntity(this.headers,HttpStatus.NOT_FOUND);
        }

        this.headers.set("message", "Se encontraron: "+ postulants.size() + " aspirantes");

        return new ResponseEntity(postulants,this.headers,HttpStatus.OK);
    }




}
