package com.bolsaTrabajo.validator;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PostulantValidator implements Validator {

    @Autowired
    private PostulantService postulantService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Postulant.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Postulant userFromRequest = (Postulant) o;

        Postulant postulant = postulantService.findByUsername(userFromRequest.getUsername());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");

        if (userFromRequest.getUsername().length() < 6 || userFromRequest.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (postulantService.findByUsername(userFromRequest.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (userFromRequest.getPassword().length() < 8 || userFromRequest.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!userFromRequest.getPasswordConfirm().equals(userFromRequest.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }

        if (postulant != null) {
            if(userFromRequest.getId() != postulant.getId()){
                errors.rejectValue(
                        "username",
                        "Duplicate.postulante.username",
                        "Ya existe un postulante con este username");
            }
        }

    }
}
