package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.compositeKeys.AcademicExperienceProfileId;
import com.bolsaTrabajo.model.jobInfo.AcademicExperienceProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("academicExperienceProfileRepository")
public interface AcademicExperienceProfileRepository extends JpaRepository<AcademicExperienceProfile, AcademicExperienceProfileId> {

}