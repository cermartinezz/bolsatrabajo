package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.Language;
import com.bolsaTrabajo.repositories.LanguageRepository;
import com.bolsaTrabajo.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mvip on 06-03-17.
 */
public class LanguageServiceImpl implements LanguageService{

    @Autowired
    private LanguageRepository languageRepository;


    @Override
    public Long count() {
        return languageRepository.count();
    }

    @Override
    public List<Language> getAllLanguages(){
        return languageRepository.findAll();
    }

    @Override
    public void store(Language language){
        languageRepository.save(language);
    }

    @Override
    public Language findById(int id) {
        return languageRepository.findById(id);
    }

    @Override
    public Language findByCodigo(String code) {
        return languageRepository.findByCodigo(code);
    }

}
