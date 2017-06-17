package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.jobInfo.AcademicExperienceProfile;
import com.bolsaTrabajo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/perfil/{id}/academico")
public class AcademicExperienceRestController {

    @Autowired
    AcademicExperienceProfile experienceProfile;

    private HttpHeaders headers;

    public AcademicExperienceRestController() {
        this.headers = new HttpHeaders();
    }

    @PostMapping
    public ResponseEntity save(@RequestBody AcademicExperienceProfile academicExperienceProfile,@PathVariable Integer id) {

        try {
            this.experienceProfile.save(academicExperienceProfile,id);
        } catch (Exception e) {
            String message = StringUtils.clearMessage(e.getCause().getCause().getMessage());
            this.headers.set("message", message);
            return new ResponseEntity(this.headers, HttpStatus.CONFLICT);
        }
        this.headers.set("message", "Se creo nuevo requisito academico");
        return new ResponseEntity(this.headers, HttpStatus.OK);

    }

    @PutMapping("/actualizar")
    public void update() {

    }

    @DeleteMapping("eliminar")
    public void delete() {

    }
}
