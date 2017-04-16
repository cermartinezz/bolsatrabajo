package com.bolsaTrabajo.validator;

import com.bolsaTrabajo.model.Certification;
import com.bolsaTrabajo.service.CertificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CertificationValidator implements Validator {

    private static final Logger log = LoggerFactory.getLogger(CertificationValidator.class);
    @Autowired
    private CertificationService certificationService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Certification.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Certification certificationFromRequest = (Certification) o;
        log.info("FromRequest {}",certificationFromRequest);
        Certification certification = certificationService.findCertificationByCode(certificationFromRequest.getCertificationCode());
        log.info("original {}",certification);

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "certificationCode", "NotEmpty","Este campo no puede ser vacio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "certificationTitle", "NotEmpty","Este campo no puede ser vacio");

        if (certification != null) {
            if(certification.getCertificationId() != certificationFromRequest.getCertificationId()){
                errors.rejectValue(
                        "certificationCode",
                        "Duplicate.certification.code",
                        "Ya existe una certificacion con este codigo");
            }
        }
    }
}
