package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.compositeKeys.AcademicExperienceID;
import com.bolsaTrabajo.model.postulantInfo.AcademicExperience;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by keepercito on 06-07-17.
 */
public interface AcademicExperienceRepository extends JpaRepository <AcademicExperience,AcademicExperienceID> {

    public AcademicExperience findByPk(AcademicExperienceID id);
}
