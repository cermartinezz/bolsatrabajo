package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.compositeKeys.WorkExperienceID;
import com.bolsaTrabajo.model.postulantInfo.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by keepercito on 06-07-17.
 */
public interface WorkExperienceRepository extends JpaRepository <WorkExperience,WorkExperienceID> {

    public WorkExperience findByPk(WorkExperienceID id);
}
