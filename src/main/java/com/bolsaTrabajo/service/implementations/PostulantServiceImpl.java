package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.Role;
import com.bolsaTrabajo.repositories.PostulantRepository;
import com.bolsaTrabajo.repositories.RoleRepository;
import com.bolsaTrabajo.repositories.UserRepository;
import com.bolsaTrabajo.service.PostulantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;

public class PostulantServiceImpl implements PostulantService {

    @Autowired
    private PostulantRepository postulantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public Postulant findUserByEmail(String email) {
        return postulantRepository.findByEmail(email);
    }



    @Override
    public void save(Postulant postulant) {
        postulant.setPassword(bCryptPasswordEncoder.encode(postulant.getPassword()));

        postulant.setActive(1);

        HashSet<Role> roleCollection = new HashSet<>();

        Role ROLE = roleRepository.findByName("POSTULANTE");

        roleCollection.add(ROLE);

        postulant.setRoles(roleCollection);

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
    public void update(Postulant postulant) {
        postulantRepository.save(postulant);
    }

}
