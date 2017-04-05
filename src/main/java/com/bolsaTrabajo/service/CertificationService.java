package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Certification;
import com.bolsaTrabajo.repositories.CertificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CertificationService {

    public static final Logger logger = LoggerFactory.getLogger(CertificationService.class);

    @Autowired
    private CertificationRepository certificationRepository;

    public List<Certification> getAllCertifications(){
        return certificationRepository.findAll();
    }

    public Certification findCertificationByCode(String code) {
        return certificationRepository.findByCertificationCode(code);
    }

    public Certification findCertificationById(int id){
        return certificationRepository.findByCertificationId(id);
    }



    public void storeCertification(Certification certification) {
        certificationRepository.save(certification);
    }

    public void updateCertification(Certification certification) {
        logger.info("Id certificacion: "+ certification.getCertificationId());
        storeCertification(certification);
    }
}
