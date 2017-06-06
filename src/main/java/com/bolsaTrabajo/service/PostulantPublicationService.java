package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.postulantInfo.PostulantPublication;
import com.bolsaTrabajo.model.compositeKeys.PostulantPublicationId;

/**
 * Created by mvip on 05-30-17.
 */
public interface PostulantPublicationService {

    PostulantPublication getPublicationOfPostulant(PostulantPublicationId postulantPublicationId);
    PostulantPublication postulantPublication(PostulantPublicationId postulantPublicationId);

    void delete(PostulantPublication postulantPublication);

}
