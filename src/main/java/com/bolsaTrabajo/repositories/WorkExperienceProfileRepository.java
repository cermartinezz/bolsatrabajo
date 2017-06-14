package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.compositeKeys.WorkExperienceProfileId;
import com.bolsaTrabajo.model.jobInfo.WorkExperienceProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("workExperienceProfileRepository")
public interface WorkExperienceProfileRepository extends JpaRepository<WorkExperienceProfile, WorkExperienceProfileId>{
    @Procedure(name = "SP_CREAR_EXPERI_LABORAL_PREFIL")
    void store(
            @Param("ID_PROFILE") Integer perfil,
            @Param("ID_PUESTO") Integer puesto,
            @Param("AÑOS") Integer años,
            @Param("NOMBRE_PUESTO") String nombre
    );
}
