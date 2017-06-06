package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.postulantInfo.PostulantPublication;
import com.bolsaTrabajo.model.compositeKeys.PostulantPublicationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mvip on 05-30-17.
 */
@Repository("postulantPublicationRepository")
public interface PostulantPublicationRepository extends JpaRepository<PostulantPublication, Integer>{

    PostulantPublication findByPrimaryKey(PostulantPublicationId postulantPublicationId);
}
