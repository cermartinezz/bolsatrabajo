package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.compositeKeys.JobProfileSkillId;
import com.bolsaTrabajo.model.jobInfo.JobProfileSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("jobProfileSkillRepository")
public interface JobProfileSkillRepository extends JpaRepository<JobProfileSkill, JobProfileSkillId> {
    @Procedure(name = "SP_CREAR_JOB_PRO_SKILL")
    void store(
            @Param("ID_HABILIDAD") Integer idHabilidad,
            @Param("NOMBRE_HABILIDAD") String nombreHabilidad,
            @Param("ID_PERFIL") Integer idPerfil,
            @Param("ID_CATEGORIA") Integer idCategoria
    );
}