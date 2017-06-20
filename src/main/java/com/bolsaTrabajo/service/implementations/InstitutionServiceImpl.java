package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.catalog.Institution;
import com.bolsaTrabajo.repositories.InstitutionRepository;
import com.bolsaTrabajo.service.InstitutionService;
import com.bolsaTrabajo.util.InstitutionType;
import com.bolsaTrabajo.util.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class InstitutionServiceImpl implements InstitutionService {


    @Autowired
    private InstitutionRepository institutionRepository;

    @Override
    public void save(Institution institution) {
        institutionRepository.save(institution);
    }

    @Override
    public Institution findInstitutionById(int id) {
        return institutionRepository.findById(id);
    }

    @Override
    public Institution findInstitutionByCode(String code) {
        return institutionRepository.findByInstitutionCode(code);
    }

    @Override
    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    @Override
    public void update(Institution currentInstitution) {
        this.save(currentInstitution);
    }

    @Override
    public void delete(Institution institutionById) {
        institutionRepository.delete(institutionById);
    }

    @Override
    public List<Institution> getInstitutionsByType(InstitutionType type) {
        return institutionRepository.findByInstitutionType(type);
    }

}
