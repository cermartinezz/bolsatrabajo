package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.Institution;
import com.bolsaTrabajo.repositories.InstitutionRepository;
import com.bolsaTrabajo.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;

public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Override
    public void save(Institution institution) {
        institutionRepository.save(institution);
    }

    @Override
    public Institution findInstitutionByCode(String code) {
        return institutionRepository.findByInstitutionCode(code);
    }
}
