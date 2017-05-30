package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Language;
import com.bolsaTrabajo.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mvip on 05-30-17.
 */
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public List<Language> getAllLanguages(){
        return languageRepository.findAll();
    }

    public void store(Language language){
        languageRepository.save(language);
    }

}
