package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Job;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.model.jobInfo.Candidate;
import com.bolsaTrabajo.model.postulantInfo.PostulantPublication;
import com.bolsaTrabajo.service.CandidateService;
import com.bolsaTrabajo.service.JobService;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by mvip on 06-14-17.
 */

@RestController
@RequestMapping("/api/postulante/{username}/puesto/")
public class CandidateRestController {

    public static final Logger logger = LoggerFactory.getLogger(PostulantPublication.class);

    @Autowired
    private Candidate candidate;

    @Autowired
    private JobService jobService;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private PostulantService postulantService;

    private HttpHeaders headers;

    public CandidateRestController() {
        this.headers = new HttpHeaders();
    }

    @GetMapping
    public ResponseEntity<List<Candidate>> getCandidates(){
        return new ResponseEntity<List<Candidate>>(HttpStatus.OK);
    }

    @GetMapping("/{job_id}")
    public ResponseEntity<Candidate> store(@PathVariable Long job_id, @PathVariable String username, UriComponentsBuilder uriBuilder){

        Candidate jobcandidate;

        Candidate candidateFromRequest = new Candidate();
        Postulant postulantFromRequest = postulantService.findByUsername(username);
        Job jobFromRequest = jobService.findById(job_id);

        candidateFromRequest.setJob(jobFromRequest);
        candidateFromRequest.setPostulant(postulantFromRequest);

        try{

            jobcandidate = this.candidate.save(username,candidateFromRequest);

        }catch (InvalidDataAccessApiUsageException e){
            this.headers.set("message","No puedes aplicar dos veces al mismo puesto.");
            return new ResponseEntity<Candidate>(this.headers,HttpStatus.CONFLICT);
        }

        this.headers.set("message","Tu aplicación a este puesto ha sido enviada");
        this.headers.setLocation(uriBuilder.path("/postulante/"+username+"/aplicaciones").buildAndExpand(username).toUri());
        return new ResponseEntity<Candidate>(jobcandidate,this.headers, HttpStatus.CREATED);

    }

    @GetMapping("/{job_id}/idioma/{id_idioma}")
    public ResponseEntity<Postulant> filtroIdiomas(){
        return new ResponseEntity<Postulant>(HttpStatus.OK);
    }

}
