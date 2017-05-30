package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.PostulantPublication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mvip on 05-30-17.
 */
@Repository("postulantPublicationRepository")
public interface PostulantPublicationRepository extends JpaRepository<PostulantPublication, Integer>{
}
