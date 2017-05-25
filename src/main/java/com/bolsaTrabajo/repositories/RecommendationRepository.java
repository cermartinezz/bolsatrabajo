package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("postulantRecomendationRepository")
public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {

    List<Recommendation> findAllByPostulant(Postulant postulant);

    Optional<Recommendation> findById(Integer in);

}
