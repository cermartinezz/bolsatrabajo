package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.catalog.Language;
import com.bolsaTrabajo.model.compositeKeys.PostulantLanguageId;
import com.bolsaTrabajo.model.postulantInfo.PostulantLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postulantLanguageRepository")
public interface PostulantLanguageRepository extends JpaRepository<PostulantLanguage,Integer>{

    PostulantLanguage findByPrimaryKey(PostulantLanguageId postulantLanguageId);
    List<PostulantLanguage> findByPrimaryKey_Language(Language language);
}
