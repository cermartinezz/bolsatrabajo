package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("certificationRepository")
public interface CertificationRepository extends JpaRepository<Certification,Integer> {
    Certification findByCertificationCode(String code);

    Certification findByCertificationId(int id);
}
