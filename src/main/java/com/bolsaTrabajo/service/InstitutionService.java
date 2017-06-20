package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.catalog.Institution;
import com.bolsaTrabajo.util.InstitutionType;

import java.util.List;
import java.util.Optional;


public interface InstitutionService {

    void save(Institution institution);

    Institution findInstitutionById(int id);

    Institution findInstitutionByCode(String code);


    List<Institution> getAllInstitutions();

    void update(Institution currentInstitution);

    List<Institution> getInstitutionsByType(InstitutionType type);

    void delete(Optional<Institution> institutionById);
}
