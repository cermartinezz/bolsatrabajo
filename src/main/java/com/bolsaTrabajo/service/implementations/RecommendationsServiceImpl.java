package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.Recommendation;
import com.bolsaTrabajo.repositories.RecommendationRepository;
import com.bolsaTrabajo.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RecommendationsServiceImpl implements RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;


    @Override
    public List<Recommendation> getRecommendations(Postulant postulant) {

        return recommendationRepository.findAllByPostulant(postulant);

    }

    @Override
    public Recommendation getRecommendation(Integer in) {
        return recommendationRepository.findById(in);
    }

    @Override
    public void store(Recommendation recommendation) {
        recommendationRepository.save(recommendation);
    }

    @Override
    public void update(Recommendation recommendation,Integer originalId) {
        Recommendation recommendationOriginal = this.getRecommendation(originalId);
        recommendationOriginal.setName(recommendation.getName());
        recommendationOriginal.setPhoneNumber(recommendation.getPhoneNumber());
        store(recommendationOriginal);
    }

    @Override
    public void detele(Recommendation recommendation) {
        recommendationRepository.delete(recommendation);
    }
}
