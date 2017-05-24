package com.bolsaTrabajo.validator;

import com.bolsaTrabajo.model.Recommendation;
import com.bolsaTrabajo.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RecommendationValidator implements Validator {

    @Autowired
    private RecommendationService recommendationService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Recommendation.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Recommendation recommendationFromRequest = (Recommendation) target;

    }
}
