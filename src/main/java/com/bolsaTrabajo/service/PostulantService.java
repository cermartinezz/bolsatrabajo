package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Postulant;

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
}
