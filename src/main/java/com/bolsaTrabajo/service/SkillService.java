package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.catalog.Skill;
import com.bolsaTrabajo.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mvip on 04-05-17.
 */
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> getAllSkills()
    {
        return skillRepository.findAll();
    }

    public void storeSkill(Skill skill){
        skillRepository.save(skill);
    }

    public Skill findSkillByCodigo(String code){
        return skillRepository.findByCodigo(code);
    }

    public void updateSkill(Skill skill){
        storeSkill(skill);
    }

    public void deleteSkill(String code){
        skillRepository.deleteByCodigo(code);
    }
}
