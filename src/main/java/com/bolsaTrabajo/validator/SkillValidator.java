package com.bolsaTrabajo.validator;

import com.bolsaTrabajo.model.Skill;
import com.bolsaTrabajo.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class SkillValidator implements Validator{

    @Autowired
    private SkillService skillService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Skill.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Skill skillFromRequest = (Skill) target;
        Skill skill = skillService.findSkillByCodigo(skillFromRequest.getCodigo());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"titulo","Este campo no puede ser vacio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"codigo","Este campo no puede ser vacio");

        if (skill != null){
            if(skillFromRequest.getId() != skill.getId()){
                errors.rejectValue(
                        "codigo",
                        "Duplicate.publication.code",
                        "Ya existe una habilidad con este codigo");

            }
        }

    }
}
