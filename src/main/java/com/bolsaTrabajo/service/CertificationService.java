package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.catalog.Certification;

import java.util.List;

public interface CertificationService {

    public List<Certification> getAllCertifications();

    public Certification findCertificationByCode(String code);

    public Certification findCertificationById(int id);

    public void storeCertification(Certification certification);

    void spNewCertification(Certification certification);

    void spUpdateCertification(Certification certification);

    public void updateCertification(Certification certification);

    public List<Certification> getCertificationsByInstitution(Integer institution);
}
