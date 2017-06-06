package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Award;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AwardRepository extends JpaRepository<Award, Long> {
    Award findByNombre(String nombre);
    Award findById(long id);
    void deleteById(long id);
}
