package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.catalog.SkillCategory;
import com.bolsaTrabajo.repositories.SkillCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public interface SkillCategoryService {

    Long count();
    SkillCategory findById(int id);
    public List<SkillCategory> getAllSkillsCategory();
    void save(SkillCategory skillCategory);
}
