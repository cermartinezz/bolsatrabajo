package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.catalog.Skill;
import com.bolsaTrabajo.model.catalog.SkillCategory;
import com.bolsaTrabajo.service.SkillCategoryService;
import com.bolsaTrabajo.service.SkillService;
import com.bolsaTrabajo.util.CustomErrorType;
import com.bolsaTrabajo.validator.SkillValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/skills")
public class SkillRestController {

    public static final Logger logger = LoggerFactory.getLogger(PublicationRestController.class);

    private HttpHeaders headers;

    @Autowired
    private SkillValidator skillValidator;

    @Autowired
    public SkillService skillService;

    @Autowired
    public SkillCategoryService skillCategoryService;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(skillValidator);
    }

    public SkillRestController() {
        this.headers = new HttpHeaders();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Skill>> getAllSkills() {
        List<Skill> skills = skillService.getAllSkills();

        if (skills == null || skills.isEmpty()){
            return new ResponseEntity<List<Skill>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Skill>>(skills, HttpStatus.OK);
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity findByCategoria(@PathVariable int categoriaId){

        SkillCategory category = skillCategoryService.findById(categoriaId);

        List<Skill> skillsByCategoria = skillService.findSkillsByCategoria(category);

        if(skillsByCategoria.size() <= 0){
            this.headers.set("message", "No se encontraron habilidades");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        this.headers.set("message","Se encontraron :" + skillsByCategoria.size() + "habilidades");
        return new ResponseEntity(skillsByCategoria,HttpStatus.OK);
    }

    @GetMapping(value= "{code}")
    public ResponseEntity show(@PathVariable("code") String code){

        Skill skill = skillService.findSkillByCodigo(code);

        if(skill == null){
            headers.set("message","No se encontraron registros");
            return new ResponseEntity(headers,HttpStatus.NOT_FOUND);
        }
        headers.set("message","Registros Encontrados");
        return new ResponseEntity(skill,headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ResponseEntity<List<SkillCategory>> getAllCategories() {
        List<SkillCategory> skillsCat = skillCategoryService.getAllSkillsCategory();

        return new ResponseEntity<List<SkillCategory>>(skillsCat, HttpStatus.OK);
    }

    @RequestMapping(value = "/names", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAllCategoriesByName() {
        List<SkillCategory> skillsCat = skillCategoryService.getAllSkillsCategory();

        List<String> names = new ArrayList<>();
        for (SkillCategory cat: skillsCat)
        {
            names.add(cat.getTitulo());
        }
        return new ResponseEntity<List<String>>(names, HttpStatus.OK);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid @RequestBody Skill skill, UriComponentsBuilder ucBuilder){

        skillService.storeSkill(skill);
        //publicationRepository.save(publication);

        this.headers.setLocation(ucBuilder.path("/habilidades").buildAndExpand(skill.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{code}")
    public ResponseEntity actualizar(@PathVariable("code") String code, @Valid @RequestBody Skill skill) {
        Skill current = skillService.findSkillByCodigo(code);

        current.setTitulo(skill.getTitulo());
        current.setCodigo(skill.getCodigo());
        current.setSkillCategory(skill.getSkillCategory());

        skillService.updateSkill(skill);

        return new ResponseEntity(current, HttpStatus.OK);
    }

    @RequestMapping(value = "/{code}",method = RequestMethod.DELETE)
    public ResponseEntity eliminar(@PathVariable("code") String code){
        Skill skill = skillService.findSkillByCodigo(code);
        if (skill == null){
            return new ResponseEntity(new CustomErrorType("No existe habilidad con ese codigo"+code),
                    HttpStatus.NOT_FOUND);
        }
        skillService.deleteSkill(code);

        return new ResponseEntity<Skill>(HttpStatus.NO_CONTENT);
    }

}
