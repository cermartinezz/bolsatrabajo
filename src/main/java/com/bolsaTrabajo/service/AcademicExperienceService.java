package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.compositeKeys.AcademicExperienceID;
import com.bolsaTrabajo.model.postulantInfo.AcademicExperience;
import com.bolsaTrabajo.repositories.AcademicExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by keepercito on 06-07-17.
 */
public class AcademicExperienceService {

    @Autowired
    AcademicExperienceRepository repository;

    public AcademicExperience getAcadExpById(AcademicExperienceID id){
        return repository.findByPk(id);
    }

    public void deleteAcadExp(AcademicExperience experience){
        repository.delete(experience);
    }
}
