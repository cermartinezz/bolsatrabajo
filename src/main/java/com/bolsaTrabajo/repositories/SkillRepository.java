package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mvip on 04-05-17.
 */
@Repository("skillRepository")
public interface SkillRepository extends JpaRepository<Skill,Integer> {
    Skill findByCodigo(String code);
}
