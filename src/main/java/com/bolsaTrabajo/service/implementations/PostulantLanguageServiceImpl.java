package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.compositeKeys.PostulantLanguageId;
import com.bolsaTrabajo.model.postulantInfo.PostulantLanguage;
import com.bolsaTrabajo.repositories.PostulantLanguageRepository;
import com.bolsaTrabajo.service.PostulantLanguageService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mvip on 06-04-17.
 */
public class PostulantLanguageServiceImpl implements PostulantLanguageService {

    @Autowired
    private PostulantLanguageRepository postulantLanguageRepository;

    @Override
    public PostulantLanguage getLanguageOfPostulant(PostulantLanguageId postulantLanguageId) {
        return  postulantLanguageRepository.findByPrimaryKey(postulantLanguageId);
    }

    @Override
    public PostulantLanguage postulantLanguage(PostulantLanguageId postulantLanguageId) {
        return postulantLanguageRepository.findByPrimaryKey(postulantLanguageId);
    }

    @Override
    public void delete(PostulantLanguage postulantLanguage) {
        postulantLanguageRepository.delete(postulantLanguage);
    }
}
