package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.catalog.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface AreaRepository extends JpaRepository<Area, Long>{
    @Procedure(name="CA_AREA")
    void saveArea(@Param("A_NOMBRE") String a_nombre,
                          @Param("A_ID") long a_id);
    Area findById(long id);
}
