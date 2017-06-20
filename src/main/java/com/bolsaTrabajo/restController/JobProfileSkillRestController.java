package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.catalog.Skill;
import com.bolsaTrabajo.model.compositeKeys.JobProfileSkillId;
import com.bolsaTrabajo.model.jobInfo.JobProfile;
import com.bolsaTrabajo.model.jobInfo.JobProfileSkill;
import com.bolsaTrabajo.service.JobProfileService;
import com.bolsaTrabajo.service.JobProfileSkillService;
import com.bolsaTrabajo.service.SkillService;
import com.bolsaTrabajo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/perfil/{id}/hablidades")
public class JobProfileSkillRestController {

    @Autowired
    private JobProfileSkill jobProfileSkill;

    @Autowired
    private JobProfileSkillService jobProfileSkillService;

    @Autowired
    private JobProfileService jobProfile;

    @Autowired
    private JobProfileService jobProfileService;

    @Autowired
    private SkillService skillService;

    private HttpHeaders headers;

    public JobProfileSkillRestController() {
        this.headers = new HttpHeaders();
    }

    @PostMapping
    public ResponseEntity save(@RequestBody JobProfileSkill jobProfileSkill,@PathVariable Integer id) {

        try {
            this.jobProfileSkill.save(jobProfileSkill,id);
        } catch (Exception e) {
            String message = StringUtils.clearMessage(e.getCause().getCause().getMessage());
            this.headers.set("message", message);
            return new ResponseEntity(this.headers, HttpStatus.CONFLICT);
        }
        this.headers.set("message", "Se creo un nuevo requerimiento de habilidad");
        return new ResponseEntity(this.headers, HttpStatus.OK);

    }

    @PutMapping("/actualizar")
    public void update() {

    }

    @DeleteMapping("/{id_skill}/eliminar")
    public void delete(@PathVariable Integer id, @PathVariable Integer id_skill) {
        JobProfile pro = jobProfileService.findById(id);
        Skill skill = skillService.findById(id_skill);

        JobProfileSkillId jobProfileSkillId = new JobProfileSkillId();
        jobProfileSkillId.setJobProfile(pro);
        jobProfileSkillId.setSkill(skill);

        JobProfileSkill jobProfileSkill = new JobProfileSkill();
        jobProfileSkill.setPrimaryKey(jobProfileSkillId);

        jobProfileSkillService.delete(jobProfileSkill);

    }
}
