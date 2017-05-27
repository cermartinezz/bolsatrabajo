package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.postulantInfo.Recommendation;

import java.util.List;
import java.util.Optional;

public interface RecommendationService {

    public List<Recommendation> getRecommendations(Postulant postulant);

    public Optional<Recommendation> getRecommendation(Integer in);

    public void store(Recommendation recommendation);

    public void update(Recommendation recommendation,Integer originalId);

    public void detele(Recommendation recommendation);
}
