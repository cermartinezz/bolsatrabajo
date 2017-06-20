package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.catalog.SubArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface SubAreaRepository extends JpaRepository<SubArea,Long>{
    @Procedure(name="CA_SUBAREA") //el mismo nombre que el proc.
    void saveSubArea(@Param("SA_ID") long sa_id,
                     @Param("SA_ID_AREA") long sa_id_area,
                     @Param("SA_NOMBRE") String sa_nombre);
    SubArea findById(long id);
}
