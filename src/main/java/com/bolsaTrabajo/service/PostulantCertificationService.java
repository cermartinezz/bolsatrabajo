package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.compositeKeys.PostulantCertificationId;
import com.bolsaTrabajo.model.postulantInfo.PostulantCertification;


public interface PostulantCertificationService {

    PostulantCertification getCertificationOfPostulant(PostulantCertificationId postulantCertificationId);
    void delete(PostulantCertification postulantCertification);

}
