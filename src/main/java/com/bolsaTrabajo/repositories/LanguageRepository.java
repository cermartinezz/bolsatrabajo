package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("languageRepository")
public interface LanguageRepository extends JpaRepository<Language,Integer>{
    Language findById(int id);
    Language findByCodigo(String codigo);

}
