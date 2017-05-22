package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Postulant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostulantRepository extends JpaRepository<Postulant,Integer> {
    Postulant findByEmail(String email);
    Postulant findByUsername(String username);
    Postulant findById(Long id);
}
