package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.postulantInfo.PostulantLanguage;
import com.bolsaTrabajo.service.LanguageService;
import com.bolsaTrabajo.service.PostulantLanguageService;
import com.bolsaTrabajo.service.PostulantService;
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

@RestController
@RequestMapping("/api/postulante/{username}/idiomas")
public class PostulantLanguageRestController {

    public static final Logger logger = LoggerFactory.getLogger(PostulantLanguage.class);

    @Autowired
    private PostulantLanguage postulantLanguage;

    @Autowired
    private PostulantService postulantService;

    @Autowired
    private LanguageService languageService;

    private HttpHeaders headers;

    @Autowired
    private PostulantLanguageService postulantLanguageService;

    public PostulantLanguageRestController() {
        this.headers = new HttpHeaders();
    }

    @PostMapping
    public ResponseEntity<PostulantLanguage> store(@Valid @RequestBody PostulantLanguage postulantLanguageFromRequest,
                                                   @PathVariable String username, UriComponentsBuilder uriBuilder){

        PostulantLanguage language;

        try{
            language = this.postulantLanguage.save(username,postulantLanguageFromRequest);
        }catch (InvalidDataAccessApiUsageException e){
            this.headers.set("message","No se pudo registrar el idioma porque ya existe asociada a este usuario");
            return new ResponseEntity<PostulantLanguage>(this.headers, HttpStatus.CONFLICT);
        }

        this.headers.set("message","Se guardo el registro");
        this.headers.setLocation(uriBuilder.path("/postulante/{username}/perfil").buildAndExpand(username).toUri());
        return new ResponseEntity<PostulantLanguage>(language,this.headers, HttpStatus.CREATED);

    }

    @DeleteMapping("/{code}/eliminar")
    public ResponseEntity<?> delete(@PathVariable String username,@PathVariable String code){

        boolean eliminado = postulantLanguage.delete(username, code);

        if(eliminado){
            this.headers.set("message","Se elimino la habilidad");
            return new ResponseEntity(this.headers,HttpStatus.OK);
        }

        this.headers.set("message","No se elimino la habilidad");
        return new ResponseEntity(this.headers,HttpStatus.CONFLICT);

    }
}
