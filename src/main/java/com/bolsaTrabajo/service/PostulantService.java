package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Postulant;

public interface PostulantService {
    void save(Postulant postulant);

    Postulant findByUsername(String username);
}
