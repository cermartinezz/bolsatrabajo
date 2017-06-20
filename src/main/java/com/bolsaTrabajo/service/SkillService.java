package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.catalog.Skill;
import com.bolsaTrabajo.model.catalog.SkillCategory;

import java.util.List;

public interface SkillService {

    List<Skill> getAllSkills();

    List<Skill> findSkillsByCategoria(SkillCategory skillCategory);

    void storeSkill(Skill skill);

    Skill findById(int id);

    Skill findSkillByCodigo(String code);

    void updateSkill(Skill skill);

    void deleteSkill(String code);
}
