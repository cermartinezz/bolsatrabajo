package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.compositeKeys.WorkExperienceID;
import com.bolsaTrabajo.model.postulantInfo.WorkExperience;
import com.bolsaTrabajo.repositories.WorkExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by keepercito on 06-07-17.
 */
public class WorkExperienceService {

    @Autowired
    WorkExperienceRepository repository;

    public WorkExperience getWorkExpById(WorkExperienceID id){
        return repository.findByPk(id);
    }

    public void deleteWorkExp(WorkExperience experience){
        repository.delete(experience);
    }
}
