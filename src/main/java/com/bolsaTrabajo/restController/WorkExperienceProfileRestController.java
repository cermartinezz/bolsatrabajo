package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.catalog.JobCat;
import com.bolsaTrabajo.model.compositeKeys.WorkExperienceProfileId;
import com.bolsaTrabajo.model.jobInfo.JobProfile;
import com.bolsaTrabajo.model.jobInfo.WorkExperienceProfile;
import com.bolsaTrabajo.service.JobCatService;
import com.bolsaTrabajo.service.JobProfileService;
import com.bolsaTrabajo.service.WorkExperienceProfileService;
import com.bolsaTrabajo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/perfil/{id}")
public class WorkExperienceProfileRestController {

    @Autowired
    private JobProfileService jobProfileService;

    @Autowired
    private WorkExperienceProfileService workExperienceProfileService;

    @Autowired
    private JobCatService jobCatService;

    @Autowired
    WorkExperienceProfile workExperienceProfile;

    private HttpHeaders headers;

    public WorkExperienceProfileRestController() {
        this.headers = new HttpHeaders();
    }

    @PostMapping
    public ResponseEntity save(@PathVariable Integer id, @RequestBody WorkExperienceProfile workExperienceProfile){

        try{
            this.workExperienceProfile.save(workExperienceProfile,id);
        }catch (Exception e){
            String message = StringUtils.clearMessage(e.getCause().getCause().getMessage());
            this.headers.set("message",message);
            return new ResponseEntity(this.headers, HttpStatus.CONFLICT);
        }
        this.headers.set("message","Se registro un nuevo cargo para este perfil");
        return new ResponseEntity(this.headers,HttpStatus.OK);

    }

    @PutMapping("/actualizar")
    public void update(){

    }

    @DeleteMapping("/cargo/{cargo_id}/eliminar")
    public void delete(@PathVariable Integer id,@PathVariable long cargo_id){
        JobCat job = jobCatService.getJob(cargo_id);
        JobProfile profile = jobProfileService.findById(id);

        WorkExperienceProfileId workExperienceProfileId = new WorkExperienceProfileId();
        workExperienceProfileId.setJob(job);
        workExperienceProfileId.setJobProfile(profile);

        WorkExperienceProfile workExperienceProfile = new WorkExperienceProfile();
        workExperienceProfile.setPrimaryKey(workExperienceProfileId);

        workExperienceProfileService.delete(workExperienceProfile);
    }
}
