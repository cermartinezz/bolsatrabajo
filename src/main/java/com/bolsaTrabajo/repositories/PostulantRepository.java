package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.postulantInfo.PostulantCertification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PostulantRepository extends JpaRepository<Postulant,Integer> {
    Postulant findByEmail(String email);
    Postulant findByUsername(String username);
    Postulant findByNit(String nit);
    Postulant findByDui(String dui);
    Postulant findByPassport(String passport);
    Postulant findByNup(String nup);
    Postulant findById(Long id);
}
