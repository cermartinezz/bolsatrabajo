package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.catalog.Certification;
import com.bolsaTrabajo.repositories.CertificationRepository;
import com.bolsaTrabajo.service.CertificationService;
import com.bolsaTrabajo.service.InstitutionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CertificationServiceImpl implements CertificationService{

    public static final Logger logger = LoggerFactory.getLogger(CertificationServiceImpl.class);

    @Autowired
    private CertificationRepository certificationRepository;

    @Autowired
    private InstitutionService institutionService;

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

    public void spNewCertification(Certification certification){

        if(certification.getInstitution().getInstitutionType() != null){
            certificationRepository.newCertification(   certification.getCertificationTitle(),
                    certification.getInstitution().getId(),
                    certification.getInstitution().getInstitutionName(),
                    certification.getInstitution().getInstitutionType().name());
        }else{
            certificationRepository.newCertification(   certification.getCertificationTitle(),
                    certification.getInstitution().getId(),
                    "",
                    "");
        }

    }

    @Override
    public void spUpdateCertification(Certification certification) {

        certificationRepository.updateCertification(certification.getCertificationId(),certification.getCertificationTitle(),certification.getInstitution().getId());

    }

    public void updateCertification(Certification certification) {

        storeCertification(certification);

    }

    public List<Certification> getCertificationsByInstitution(Integer institution) {

        return certificationRepository.findByInstitution_Id(institution);

    }
}
