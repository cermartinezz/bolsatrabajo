package com.bolsaTrabajo.validator;

import com.bolsaTrabajo.model.Institution;
import com.bolsaTrabajo.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class InstitutionValidator implements Validator {

    @Autowired
    private InstitutionService institutionService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Institution.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Institution institutionFromRequest = (Institution) target;

        Institution institution = institutionService.findInstitutionByCode(institutionFromRequest.getInstitutionCode());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"institutionName","Este campo no puede ser vacio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"institutionCode","Este campo no puede ser vacio");

        if (institution != null) {
            if(institution.getId() != institutionFromRequest.getId()){
                errors.rejectValue(
                        "institutionCode",
                        "Duplicate.institution.code",
                        "Ya existe una institution con este codigo");
            }
        }
    }
}
