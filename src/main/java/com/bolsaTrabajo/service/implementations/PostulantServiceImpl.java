package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.repositories.PostulantRepository;
import com.bolsaTrabajo.repositories.UserRepository;
import com.bolsaTrabajo.service.PostulantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PostulantServiceImpl implements PostulantService {

    @Autowired
    private PostulantRepository postulantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public Postulant findUserByEmail(String email) {
        return postulantRepository.findByEmail(email);
    }


    @Override
    public void save(Postulant postulant) {

        postulantRepository.save(postulant);

    }

    @Override
    public Postulant findByUsername(String username) {
        return postulantRepository.findByUsername(username);
    }

    @Override
    public Postulant findById(Long id) {
        return postulantRepository.findById(id);
    }

    @Override
    public Postulant findByNup(String nup) {
        return postulantRepository.findByNup(nup);
    }

    @Override
    public Postulant findByDui(String dui) {
        return postulantRepository.findByDui(dui);
    }

    @Override
    public Postulant findByNit(String nit) {
        return postulantRepository.findByNit(nit);
    }

    @Override
    public Postulant findByPassport(String passport) {
        return postulantRepository.findByPassport(passport);
    }

    @Override
    public Postulant findByEmail(String email) {
        return postulantRepository.findByEmail(email);
    }

    @Override
    public void update(Postulant postulant) {
        postulantRepository.save(postulant);
    }

    public void delete(Postulant postulant){postulantRepository.delete(postulant);}

}
