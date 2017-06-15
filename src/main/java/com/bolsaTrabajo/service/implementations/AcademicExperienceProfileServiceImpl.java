package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.jobInfo.AcademicExperienceProfile;
import com.bolsaTrabajo.repositories.AcademicExperienceProfileRepository;
import com.bolsaTrabajo.service.AcademicExperienceProfileService;
import org.springframework.beans.factory.annotation.Autowired;

public class AcademicExperienceProfileServiceImpl implements AcademicExperienceProfileService {

    @Autowired
    private AcademicExperienceProfileRepository academicExperienceProfileRepository;

    @Override
    public void save(AcademicExperienceProfile academicExperienceProfile) {

    }

    @Override
    public void update(AcademicExperienceProfile academicExperienceProfile) {

    }

    @Override
    public void delete(AcademicExperienceProfile academicExperienceProfile) {

    }
}
