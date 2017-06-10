package com.bolsaTrabajo.validator;

import com.bolsaTrabajo.model.catalog.Certification;
import com.bolsaTrabajo.model.catalog.Institution;
import com.bolsaTrabajo.service.CertificationService;
import com.bolsaTrabajo.service.InstitutionService;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Optional;

public class CertificationValidator implements Validator {

    private static final Logger log = LoggerFactory.getLogger(CertificationValidator.class);

    @Autowired
    private CertificationService certificationService;

    @Autowired
    private InstitutionService institutionService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Certification.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Certification certificationFromRequest = (Certification) o;

        Institution institution = institutionService.findInstitutionById(certificationFromRequest.getInstitution().getId());

        String code = certificationFromRequest.getCertificationCode().trim() + '-' + institution.getInstitutionCode().trim();

        String title = WordUtils.capitalize(certificationFromRequest.getCertificationTitle().trim());

        certificationFromRequest.setCertificationTitle(title);

        certificationFromRequest.setCertificationCode(code.toUpperCase());


        Certification certification = certificationService.findCertificationByCode(certificationFromRequest.getCertificationCode());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "certificationCode", "NotEmpty","Este campo no puede ser vacio");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "certificationTitle", "NotEmpty","Este campo no puede ser vacio");

        if (certification != null) {
            if(certification.getCertificationId() != certificationFromRequest.getCertificationId()){
                errors.rejectValue(
                        "certificationCode",
                        "Duplicate.certification.code",
                        "Ya existe una certificacion con este codigo para esta institucion");
            }
        }
    }
}
