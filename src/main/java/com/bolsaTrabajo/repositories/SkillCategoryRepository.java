package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mvip on 04-05-17.
 */
@Repository("skillCategoryRepository")
public interface SkillCategoryRepository extends JpaRepository<SkillCategory,Integer> {
}
