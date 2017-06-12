package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.model.jobInfo.JobProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jobProfileRepository")
public interface JobProfileRepository extends JpaRepository<JobProfile, Integer> {

    JobProfile findById(int id);
    JobProfile findByCode(String code);
    List<JobProfile> findAllByCompany(Company company);

    @Procedure(name = "SP_CREAR_PERFIL_TRABAJO")
    Integer store(  @Param("NOMBRE") String nombre,
                    @Param("DESCRIPCION") String descripcion,
                    @Param("EDAD_MAX") Integer edadMax,
                    @Param("EDAD_MIN") Integer edadMin,
                    @Param("COMPAÑIA_ID") Long compañia);

    @Procedure(name = "SP_ACTUALIZAR_PERFIL_TRABAJO")
    void update(  @Param("ID_JP") Integer id,
                     @Param("NOMBRE") String nombre,
                     @Param("DESCRIPCION") String descripcion,
                     @Param("EDAD_MAX") Integer edadMax,
                     @Param("EDAD_MIN") Integer edadMin,
                     @Param("COMPAÑIA_ID") Long compañia);

}
