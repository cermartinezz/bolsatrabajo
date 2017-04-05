package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Skill;
import com.bolsaTrabajo.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mvip on 04-05-17.
 */
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public void storeSkill(Skill skill){
        skillRepository.save(skill);
    }

    public Skill findSkillByCodigo(String code){
        return skillRepository.findByCodigo(code);
    }
}
