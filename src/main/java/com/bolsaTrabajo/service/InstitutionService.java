package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Institution;

import java.util.List;


public interface InstitutionService {
    void save(Institution institution);
    Institution findInstitutionByCode(String code);

    List<Institution> getAllInstitutions();


    void update(Institution currentInstitution);
}
