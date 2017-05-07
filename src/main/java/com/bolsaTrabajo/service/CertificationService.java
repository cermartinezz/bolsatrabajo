package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Certification;
import com.bolsaTrabajo.model.Institution;
import com.bolsaTrabajo.repositories.CertificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CertificationService {

    public List<Certification> getAllCertifications();

    public Certification findCertificationByCode(String code);

    public Certification findCertificationById(int id);

    public void storeCertification(Certification certification);

    public void updateCertification(Certification certification);

    public List<Certification> getCertificationsByInstitution(Integer institution);
}
