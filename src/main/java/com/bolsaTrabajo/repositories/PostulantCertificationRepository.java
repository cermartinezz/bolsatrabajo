package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.compositeKeys.PostulantCertificationId;
import com.bolsaTrabajo.model.postulantInfo.PostulantCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postulantCertificationRepository")
public interface PostulantCertificationRepository extends JpaRepository<PostulantCertification, PostulantCertificationId> {

//    List<PostulantCertification> findAllByPostulant_Id(Integer postulant_id);

}
