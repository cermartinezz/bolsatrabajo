package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.PostulantCertification;
import com.bolsaTrabajo.repositories.PostulantCertificationRepository;
import com.bolsaTrabajo.service.PostulantCertificationService;
import com.bolsaTrabajo.service.PostulantService;
import org.springframework.beans.factory.annotation.Autowired;

public class PostulantCertificationImpl implements PostulantCertificationService {

    @Autowired
    private PostulantCertificationRepository postulantCertificationRepository;

    @Override
    public void save(PostulantCertification postulantCertification) {

        postulantCertificationRepository.save(postulantCertification);

    }
}
