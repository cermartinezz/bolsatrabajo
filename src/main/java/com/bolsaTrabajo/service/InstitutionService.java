package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Institution;


public interface InstitutionService {
    void save(Institution institution);
    Institution findInstitutionByCode(String code);
}
