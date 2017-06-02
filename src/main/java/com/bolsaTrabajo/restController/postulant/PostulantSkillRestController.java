package com.bolsaTrabajo.restController.postulant;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.Skill;
import com.bolsaTrabajo.model.compositeKeys.PostulantSkillId;
import com.bolsaTrabajo.model.postulantInfo.PostulantSkill;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.service.PostulantSkillService;
import com.bolsaTrabajo.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/postulante/{username}/habilidades")
public class PostulantSkillRestController {


    @Autowired
    private PostulantSkill postulantSkill;

    private HttpHeaders headers;

    public PostulantSkillRestController() {
        this.headers = new HttpHeaders();
    }

    @GetMapping
    public ResponseEntity<List<PostulantSkill>> getSkills(@PathVariable String username){
        return new ResponseEntity<List<PostulantSkill>>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostulantSkill> store(@RequestBody PostulantSkill postulantSkillFromRequest,
                                @PathVariable String username,
                                UriComponentsBuilder uriBuilder){
        PostulantSkill skill;
        try{
             skill = postulantSkill.save(username, postulantSkillFromRequest);
        }catch (Exception e){
            this.headers.set("message","No se pudo registrar la habilidad, porque ya existe asociada a este usuario");
            return new ResponseEntity<PostulantSkill>(this.headers,HttpStatus.CONFLICT);
        }

        this.headers.set("message", "Se guardo la habilidad");
        this.headers.setLocation(uriBuilder.path("/postulante/{username}/perfil").buildAndExpand(username).toUri());
        return new ResponseEntity<PostulantSkill>(skill,this.headers,HttpStatus.OK);
    }

    @DeleteMapping("/{code}/eliminar")
    public ResponseEntity<?> destroy(@PathVariable String username,@PathVariable String code){

        boolean eliminado = postulantSkill.delete(username, code);

        if(eliminado){
            this.headers.set("message","Se elimino la habilidad");
            return new ResponseEntity(this.headers,HttpStatus.OK);
        }

        this.headers.set("message","No se elimino la habilidad");
        return new ResponseEntity(this.headers,HttpStatus.CONFLICT);

    }

}
