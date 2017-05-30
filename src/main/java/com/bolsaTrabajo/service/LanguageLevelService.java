package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.LanguageLevel;
import com.bolsaTrabajo.repositories.LanguageLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mvip on 05-30-17.
 */
public class LanguageLevelService {

    @Autowired
    private LanguageLevelRepository languageLevelRepository;

    public List<LanguageLevel> getAllLanguageLevels(){
        return languageLevelRepository.findAll();
    }

    public void store(LanguageLevel languageLevel){
        languageLevelRepository.save(languageLevel);
    }
}
