package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mvip on 04-04-17.
 */
@Repository("publicationRepository")
public interface PublicationRepository extends JpaRepository<Publication,Integer> {
        Publication findByCodigo(String code);
        Publication findById(int id);
}
