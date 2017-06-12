package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.jobInfo.JobProfile;
import com.bolsaTrabajo.service.CompanyService;
import com.bolsaTrabajo.service.JobProfileService;
import com.bolsaTrabajo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa/{username}/perfiles")
public class JobProfileRestController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private JobProfileService jobProfileService;

    @Autowired
    private JobProfile profile;

    private HttpHeaders headers;


    public JobProfileRestController() {
        this.headers = new HttpHeaders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobProfile> findById(@PathVariable Integer id){

        JobProfile jobProfile = jobProfileService.findById(id);

        if(jobProfile.equals(null)){
            this.headers.set("message","No se encontro ninguna informacion sobre el perfil");
            return new ResponseEntity<JobProfile>(this.headers,HttpStatus.NOT_FOUND);
        }
        this.headers.set("message","Se encontro informacion sobre el perfil");
        return new ResponseEntity<JobProfile>(jobProfile,this.headers,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JobProfile> save(@RequestBody JobProfile jobProfile){
        JobProfile jobProfile1;

        try{
            Integer id = this.profile.save(jobProfile);
            jobProfile1 = jobProfileService.findById(id);
        }catch (Exception e){
            String message = StringUtils.clearMessage(e.getCause().getCause().getMessage());
            this.headers.set("message",message);
            return new ResponseEntity<JobProfile>(this.headers,HttpStatus.CONFLICT);
        }
        this.headers.set("message", "Se guardo el perfil");
        return new ResponseEntity<JobProfile>(jobProfile1,this.headers, HttpStatus.OK);
    }

}
