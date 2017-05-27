package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.postulantInfo.PostulantCertification;
import com.bolsaTrabajo.repositories.PostulantCertificationRepository;
import com.bolsaTrabajo.service.PostulantCertificationService;
import org.springframework.beans.factory.annotation.Autowired;

public class PostulantCertificationImpl implements PostulantCertificationService {

    @Autowired
    private PostulantCertificationRepository postulantCertificationRepository;

    @Override
    public void save(PostulantCertification postulantCertification) {

        postulantCertificationRepository.save(postulantCertification);

    }
}
