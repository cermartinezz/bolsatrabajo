package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.catalog.SkillCategory;
import com.bolsaTrabajo.repositories.SkillCategoryRepository;
import com.bolsaTrabajo.service.SkillCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SkillCategoryServiceImpl implements SkillCategoryService {

    @Autowired
    private
    SkillCategoryRepository skillCategoryRepository;

    @Override
    public List<SkillCategory> getAllSkillsCategory() {
        return skillCategoryRepository.findAll();
    }

    @Override
    public Long count() {
        return skillCategoryRepository.count();
    }

    @Override
    public void save(SkillCategory skillCategory) {
        skillCategoryRepository.save(skillCategory);
    }

    @Override
    public SkillCategory findById(int id) {
        return skillCategoryRepository.findById(id);
    }
}
