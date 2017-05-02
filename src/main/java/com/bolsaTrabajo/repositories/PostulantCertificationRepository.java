package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.PostulantCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("postulantCertificationRepository")
public interface PostulantCertificationRepository extends JpaRepository<PostulantCertification, Integer> {


}
