package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.jobInfo.JobProfileSkill;
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

    @DeleteMapping("/eliminar")
    public void delete() {

    }
}
