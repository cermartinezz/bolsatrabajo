package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.catalog.Skill;
import com.bolsaTrabajo.model.catalog.SkillCategory;
import com.bolsaTrabajo.repositories.SkillRepository;
import com.bolsaTrabajo.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SkillServiceImpl implements SkillService{

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public Skill findById(int id) {
       return skillRepository.findById(id);
    }

    @Override
    public List<Skill> getAllSkills()
    {
        return skillRepository.findAll();
    }

    @Override
    public List<Skill> findSkillsByCategoria(SkillCategory skillCategory) {
        return skillRepository.findAllBySkillCategory(skillCategory);
    }

    @Override
    public void storeSkill(Skill skill){
        skillRepository.save(skill);
    }

    @Override
    public Skill findSkillByCodigo(String code){
        return skillRepository.findByCodigo(code);
    }

    @Override
    public void updateSkill(Skill skill){
        storeSkill(skill);
    }

    @Override
    public void deleteSkill(String code){
        skillRepository.deleteByCodigo(code);
    }
}
