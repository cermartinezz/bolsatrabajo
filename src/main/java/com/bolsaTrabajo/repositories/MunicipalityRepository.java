package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.catalog.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mvip on 06-12-17.
 */
@Repository("municipalityRepository")
public interface MunicipalityRepository extends JpaRepository<Municipality,Integer>{
    Municipality findByNombre(String nombre);
}
