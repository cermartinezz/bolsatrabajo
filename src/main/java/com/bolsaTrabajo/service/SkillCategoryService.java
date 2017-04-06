package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.SkillCategory;
import com.bolsaTrabajo.repositories.SkillCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mvip on 04-05-17.
 */
public class SkillCategoryService {

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;

    public List<SkillCategory> getAllSkillsCategory(){
        return skillCategoryRepository.findAll();
    }
}
