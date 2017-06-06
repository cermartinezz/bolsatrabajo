package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.compositeKeys.PostulantLanguageId;
import com.bolsaTrabajo.model.postulantInfo.PostulantLanguage;

/**
 * Created by mvip on 06-04-17.
 */
public interface PostulantLanguageService {
    PostulantLanguage getLanguageOfPostulant(PostulantLanguageId postulantLanguageId);
    PostulantLanguage postulantLanguage(PostulantLanguageId postulantLanguageId);
    void delete(PostulantLanguage postulantLanguage);
}
