package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mvip on 05-30-17.
 */
@Repository("languageRepository")
public interface LanguageRepository extends JpaRepository<Language,Integer>{

}
