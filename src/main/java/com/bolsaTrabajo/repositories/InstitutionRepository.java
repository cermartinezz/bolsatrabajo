package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("institutionRepository")
public interface InstitutionRepository extends JpaRepository<Institution,Long> {
    Institution findByInstitutionCode(String code);
    Institution findById(Long id);
}
