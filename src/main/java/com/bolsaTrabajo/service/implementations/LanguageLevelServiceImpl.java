package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.LanguageLevel;
import com.bolsaTrabajo.repositories.LanguageLevelRepository;
import com.bolsaTrabajo.service.LanguageLevelService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LanguageLevelServiceImpl implements LanguageLevelService{
    @Autowired
    private LanguageLevelRepository languageLevelRepository;

    @Override
    public Long count(){
        return languageLevelRepository.count();
    }

    @Override
    public List<LanguageLevel> getAllLanguageLevels(){
        return languageLevelRepository.findAll();
    }

    @Override
    public void store(LanguageLevel languageLevel){
        languageLevelRepository.save(languageLevel);
    }
}
