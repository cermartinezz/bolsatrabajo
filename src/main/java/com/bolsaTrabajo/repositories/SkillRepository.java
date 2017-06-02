package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.catalog.Skill;
import com.bolsaTrabajo.model.catalog.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("skillRepository")
public interface SkillRepository extends JpaRepository<Skill,Integer> {
    Skill findByCodigo(String code);
    void deleteByCodigo(String code);
    Skill findById(int id);
    List<Skill> findAllBySkillCategory(SkillCategory skillCategory);
}
