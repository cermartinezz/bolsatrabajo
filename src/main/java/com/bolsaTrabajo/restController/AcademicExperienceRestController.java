package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.util.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class AcademicExperienceRestController {

    private HttpHeaders headers;

    public AcademicExperienceRestController() {
        this.headers = new HttpHeaders();
    }

    @PostMapping
    public ResponseEntity save() {

        try {

        } catch (Exception e) {
            String message = StringUtils.clearMessage(e.getCause().getCause().getMessage());
            this.headers.set("message", message);
            return new ResponseEntity(this.headers, HttpStatus.CONFLICT);
        }
        this.headers.set("message", "");
        return new ResponseEntity(this.headers, HttpStatus.OK);

    }

    @PutMapping("/actualizar")
    public void update() {

    }

    @DeleteMapping("eliminar")
    public void delete() {

    }
}
