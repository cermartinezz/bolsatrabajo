package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.catalog.Municipality;
import com.bolsaTrabajo.repositories.MunicipalityRepository;
import com.bolsaTrabajo.service.MunicipalityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mvip on 06-12-17.
 */
public class MunicipalityServiceImpl implements MunicipalityService {

    @Autowired
    private MunicipalityRepository municipalityRepository;

    @Override
    public List<Municipality> getAllMunicipalities() {
        return municipalityRepository.findAll();
    }
}
