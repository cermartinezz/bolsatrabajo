package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.catalog.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("skillCategoryRepository")
public interface SkillCategoryRepository extends JpaRepository<SkillCategory,Integer> {

    SkillCategory findById(int id);
}
