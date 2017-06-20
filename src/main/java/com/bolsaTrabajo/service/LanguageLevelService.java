package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.catalog.LanguageLevel;

import java.util.List;

/**
 * Created by mvip on 05-30-17.
 */
public interface LanguageLevelService {

    Long count();

    public List<LanguageLevel> getAllLanguageLevels();

    public void store(LanguageLevel languageLevel);

    LanguageLevel findById(int i);
}
