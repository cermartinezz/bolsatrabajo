package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.catalog.Institution;

import java.util.List;
import java.util.Optional;


public interface InstitutionService {

    void save(Institution institution);

    Optional<Institution> findInstitutionById(int id);

    Institution findInstitutionByCode(String code);


    List<Institution> getAllInstitutions();

    void update(Institution currentInstitution);

    void delete(Optional<Institution> institutionById);
}
