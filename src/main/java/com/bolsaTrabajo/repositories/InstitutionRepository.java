package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.catalog.Institution;
import com.bolsaTrabajo.util.InstitutionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("institutionRepository")
public interface InstitutionRepository extends JpaRepository<Institution,Integer> {
    Institution findByInstitutionCode(String code);

    Institution findById(int id);

    List<Institution> findByInstitutionType(InstitutionType type);
}
