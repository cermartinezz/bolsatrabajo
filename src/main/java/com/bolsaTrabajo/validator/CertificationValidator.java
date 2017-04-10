package com.bolsaTrabajo.validator;

import com.bolsaTrabajo.model.Certification;
import com.bolsaTrabajo.service.CertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CertificationValidator implements Validator {

    @Autowired
    private CertificationService certificationService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Certification.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Certification certification = (Certification) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "certificationCode", "NotEmpty","Este campo no puede ser vacio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "certificationTitle", "NotEmpty","Este campo no puede ser vacio");

        if (certificationService.findCertificationByCode(certification.getCertificationCode()) != null) {
            errors.rejectValue(
                    "certificationCode",
                    "Duplicate.certification.code",
                    "Ya existe una certificacion con este codigo");
        }
    }
}
