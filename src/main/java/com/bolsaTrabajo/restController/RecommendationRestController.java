package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.Recommendation;
import com.bolsaTrabajo.service.RecommendationService;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.validator.RecommendationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recomendaciones/{username}")
public class RecommendationRestController {

    private HttpHeaders headers;

    public RecommendationRestController() {
        this.headers = new HttpHeaders();
    }

    @Autowired
    private RecommendationValidator recommendationValidator;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private PostulantService postulantService;

    @InitBinder
    private void initBinder(WebDataBinder binder){
        binder.setValidator(recommendationValidator);
    }

    @GetMapping
    public ResponseEntity<List<Recommendation>> index(@PathVariable String username){

        Postulant postulant = postulantService.findByUsername(username);

        List<Recommendation> recommendations = recommendationService.getRecommendations(postulant);

        if(recommendations.isEmpty()){
            return new ResponseEntity<List<Recommendation>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Recommendation>>(recommendations,this.headers,HttpStatus.OK);
    }

    @GetMapping("/{recomendationId}")
    public ResponseEntity show(@PathVariable Integer recomendationId){


        Optional<Recommendation> recommendation = recommendationService.getRecommendation(recomendationId);

        if(recommendation.get().equals(null)){
            return new ResponseEntity<List<Recommendation>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(recommendation.get(),this.headers,HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity store(@Valid @RequestBody Recommendation recommendation,
                                @PathVariable String username,
                                UriComponentsBuilder uriBuilder){

        Postulant postulant = postulantService.findByUsername(username);
        recommendation.setPostulant(postulant);
        recommendationService.store(recommendation);

        this.headers.set("message","El registro fue registrado");
        this.headers.setLocation(uriBuilder.path("/postulante/{username}/perfil").buildAndExpand(username).toUri());


        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/recomendacion/{recommendationId}")
    public ResponseEntity update(@Valid @RequestBody Recommendation recommendation,
                                @PathVariable String username,@PathVariable Integer recommendationId,
                                UriComponentsBuilder uriBuilder){

        recommendationService.update(recommendation,recommendationId);

        this.headers.setLocation(uriBuilder.path("/postulante/{username}/perfil").buildAndExpand(username).toUri());

        this.headers.set("message","El registro fue actualizado");

        return new ResponseEntity(this.headers, HttpStatus.OK);
    }

    @DeleteMapping("/recomendacion/{recomendationId}")
    public ResponseEntity destroy(@PathVariable String username,@PathVariable Integer recomendationId){

        Optional<Recommendation> recommendation = recommendationService.getRecommendation(recomendationId);

        recommendationService.detele(recommendation.get());

        this.headers.set("message","El registro fue eliminado");

        return new ResponseEntity(this.headers, HttpStatus.OK);
    }

}
