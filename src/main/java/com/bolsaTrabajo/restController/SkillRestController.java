package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Skill;
import com.bolsaTrabajo.model.SkillCategory;
import com.bolsaTrabajo.service.SkillCategoryService;
import com.bolsaTrabajo.service.SkillService;
import com.bolsaTrabajo.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by mvip on 04-05-17.
 */
@RestController
@RequestMapping(value = "/skills")
public class SkillRestController {

    @Autowired
    public SkillService skillService;

    @Autowired
    public SkillCategoryService skillCategoryService;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Skill>> getAllSkills() {
        List<Skill> skills = skillService.getAllSkills();

        return new ResponseEntity<List<Skill>>(skills, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ResponseEntity<List<SkillCategory>> getAllCategories() {
        List<SkillCategory> skillsCat = skillCategoryService.getAllSkillsCategory();

        return new ResponseEntity<List<SkillCategory>>(skillsCat, HttpStatus.OK);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Skill skill, UriComponentsBuilder ucBuilder){

        Skill sk = skillService.findSkillByCodigo(skill.getCodigo());
        if (sk != null){
            return new ResponseEntity<>(new CustomErrorType("La habilidad ya existe"), HttpStatus.CONFLICT);
        }
        skillService.storeSkill(skill);
        //publicationRepository.save(publication);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/skills/{id}").buildAndExpand(skill.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
}
