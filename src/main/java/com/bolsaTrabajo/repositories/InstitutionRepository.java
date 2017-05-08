package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("institutionRepository")
public interface InstitutionRepository extends JpaRepository<Institution,Integer> {
    Institution findByInstitutionCode(String code);

    Optional<Institution> findById(Integer id);
}
