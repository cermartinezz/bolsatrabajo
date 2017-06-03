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


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dui", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nit", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passport", "NotEmpty");

        if (userFromRequest.getUsername().length() < 5 || userFromRequest.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        if (userFromRequest.getPassword().length() < 6 || userFromRequest.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!userFromRequest.getPasswordConfirm().equals(userFromRequest.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }

        Postulant postulant = postulantService.findByUsername(userFromRequest.getUsername());
        if (postulant != null) {
            if(userFromRequest.getId() != postulant.getId()){
                errors.rejectValue(
                        "username",
                        "Duplicate.postulante.username",
                        "Ya existe un postulante con este username");
            }
        }

        Postulant postulant1 = postulantService.findByDui(userFromRequest.getDui());
        if (postulant1 != null) {
            if(userFromRequest.getId() != postulant1.getId()){
                errors.rejectValue(
                        "dui",
                        "Duplicate.postulante.dui",
                        "Ya existe un postulante con este dui");
            }
        }

        Postulant postulant2 = postulantService.findByNit(userFromRequest.getNit());
        if (postulant2 != null) {
            if(userFromRequest.getId() != postulant2.getId()){
                errors.rejectValue(
                        "nit",
                        "Duplicate.postulante.nit",
                        "Ya existe un postulante con este nit");
            }
        }

        Postulant postulant3 = postulantService.findByPassport(userFromRequest.getPassport());
        if (postulant3 != null) {
            if(userFromRequest.getId() != postulant3.getId()){
                errors.rejectValue(
                        "passport",
                        "Duplicate.postulante.pasaporte",
                        "Ya existe un postulante con este pasaporte");
            }
        }

        Postulant postulant4 = postulantService.findByNup(userFromRequest.getNup());
        if (postulant4 != null) {
            if(userFromRequest.getId() != postulant4.getId()){
                errors.rejectValue(
                        "nup",
                        "Duplicate.postulante.nup",
                        "Ya existe un postulante con este nup");
            }
        }

        Postulant postulant5 = postulantService.findByEmail(userFromRequest.getEmail());
        if (postulant5 != null) {
            if(userFromRequest.getId() != postulant5.getId()){
                errors.rejectValue(
                        "email",
                        "Duplicate.postulante.email",
                        "Ya existe un postulante con este email");
            }
        }

    }
}
