package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.jobInfo.JobProfileLanguage;
import com.bolsaTrabajo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/perfil/{id}/languajes")
public class JobProfileLanguageRestController {

    @Autowired
    private JobProfileLanguage JobProfileLanguage;

    private HttpHeaders headers;

    public JobProfileLanguageRestController() {
        this.headers = new HttpHeaders();
    }

    @PostMapping
    public ResponseEntity save(@RequestBody JobProfileLanguage jobProfileLanguage, @PathVariable Integer id) {

        try {
            this.JobProfileLanguage.save(jobProfileLanguage, id);
        } catch (Exception e) {
            String message = StringUtils.clearMessage(e.getCause().getCause().getMessage());
            this.headers.set("message", message);
            return new ResponseEntity(this.headers, HttpStatus.CONFLICT);
        }
        this.headers.set("message", "Se guardo su requisito de idioma");
        return new ResponseEntity(this.headers, HttpStatus.OK);

    }

    @PutMapping("/actualizar")
    public void update() {

    }

    @DeleteMapping("/eliminar")
    public void delete() {

    }
}
