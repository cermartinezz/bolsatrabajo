package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.postulantInfo.PostulantPublication;
import com.bolsaTrabajo.model.compositeKeys.PostulantPublicationId;
import com.bolsaTrabajo.repositories.PostulantPublicationRepository;
import com.bolsaTrabajo.service.PostulantPublicationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mvip on 06-02-17.
 */
public class PostulantPublicationServiceImpl implements PostulantPublicationService{

    @Autowired
    private PostulantPublicationRepository postulantPublicationRepository;

    @Override
    public PostulantPublication getPublicationOfPostulant(PostulantPublicationId postulantCertificationId) {
        return postulantPublicationRepository.findByPrimaryKey(postulantCertificationId);
    }
}
