package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.compositeKeys.JobProfileLanguageId;
import com.bolsaTrabajo.model.jobInfo.JobProfileLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("JobProfileLanguageRepository")
public interface JobProfileLanguageRepository extends JpaRepository<JobProfileLanguage, JobProfileLanguageId> {
    
    @Procedure(name = "SP_CREAR_JOB_PRO_LANG")
    void store(
            @Param("ID_PERFIL") Integer idPerfil,
            @Param("ID_NIVEL") Integer idNivel,
            @Param("ID_LENGUAJE") Integer idLenguaje
    );
}