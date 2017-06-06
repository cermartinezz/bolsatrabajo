package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Language;
import com.bolsaTrabajo.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mvip on 05-30-17.
 */
public interface LanguageService {

    Long count();
    List<Language> getAllLanguages();
    public void store(Language language);
    Language findById(int id);
    Language findByCodigo(String code);

}
