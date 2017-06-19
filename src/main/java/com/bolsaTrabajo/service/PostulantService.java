package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.Language;
import com.bolsaTrabajo.model.postulantInfo.PostulantCertification;
import com.bolsaTrabajo.model.postulantInfo.PostulantLanguage;

import java.util.List;

public interface PostulantService {
    void save(Postulant postulant);

    Postulant findByUsername(String username);

    Postulant findById(Long id);

    Postulant findByNup(String nup);

    Postulant findByDui(String dui);

    Postulant findByNit(String nit);

    Postulant findByPassport(String passport);

    Postulant findByEmail(String email);

    void update(Postulant postulant);

    void delete(Postulant postulant);

    List<Postulant> getAll();

    List<PostulantLanguage> getPostulantForLanguage(Language language);
}
