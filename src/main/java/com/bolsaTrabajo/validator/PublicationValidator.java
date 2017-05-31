package com.bolsaTrabajo.validator;

import com.bolsaTrabajo.model.postulantInfo.Publication;
import com.bolsaTrabajo.service.PublicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PublicationValidator implements  Validator{

    private Logger log = LoggerFactory.getLogger(PublicationValidator.class);

    @Autowired
    private PublicationService publicationService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Publication.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Publication publicationFromRequest = (Publication) target;
        Publication publication = publicationService.findPublicationByCodigo(publicationFromRequest.getCodigo());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"titulo","Este campo no puede ser vacio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"codigo","Este campo no puede ser vacio");

        if (publication != null) {
            if(publicationFromRequest.getId() != publication.getId()){
                errors.rejectValue(
                        "codigo",
                        "Duplicate.publication.code",
                        "Ya existe una publicacion con este codigo");
            }
        }
    }

}
