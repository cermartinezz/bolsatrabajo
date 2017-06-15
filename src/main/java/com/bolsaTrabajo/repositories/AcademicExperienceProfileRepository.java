package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.compositeKeys.AcademicExperienceProfileId;
import com.bolsaTrabajo.model.jobInfo.AcademicExperienceProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("academicExperienceProfileRepository")
public interface AcademicExperienceProfileRepository extends JpaRepository<AcademicExperienceProfile, AcademicExperienceProfileId> {
    @Procedure(name = "SP_CREAR_EXP_ACADEMICA_PERFIL")
    void store(
            @Param("ID_PERFIL") Integer idProfile,
            @Param("ID_TITULO") Integer idTitle,
            @Param("NOMBRE_TITULO") String nameTitle
    );
}