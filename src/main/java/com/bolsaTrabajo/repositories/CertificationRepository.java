package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.catalog.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("certificationRepository")
public interface CertificationRepository extends JpaRepository<Certification,Integer> {

    Certification findByCertificationCode(String code);

    Certification findByCertificationId(int id);

    List<Certification> findByInstitution_Id(Integer institution);

}
