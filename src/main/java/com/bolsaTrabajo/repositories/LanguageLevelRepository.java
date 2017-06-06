package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.catalog.LanguageLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mvip on 05-30-17.
 */
@Repository("languageLevelRepository")
public interface LanguageLevelRepository extends JpaRepository<LanguageLevel,Integer>{
}
