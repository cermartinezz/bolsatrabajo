package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.compositeKeys.PostulantCertificationId;
import com.bolsaTrabajo.model.postulantInfo.PostulantCertification;
import com.bolsaTrabajo.repositories.PostulantCertificationRepository;
import com.bolsaTrabajo.service.PostulantCertificationService;
import org.springframework.beans.factory.annotation.Autowired;

public class PostulantCertificationServiceImpl implements PostulantCertificationService {

    @Autowired
    private PostulantCertificationRepository postulantCertificationRepository;

    @Override
    public PostulantCertification getCertificationOfPostulant(PostulantCertificationId postulantCertificationId) {
        return postulantCertificationRepository.findByPrimaryKey(postulantCertificationId);
    }

    @Override
    public void delete(PostulantCertification postulantCertification) {
        postulantCertificationRepository.delete(postulantCertification);
    }
}
