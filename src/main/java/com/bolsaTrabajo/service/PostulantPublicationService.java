package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.PostulantPublication;
import com.bolsaTrabajo.repositories.PostulantPublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mvip on 05-30-17.
 */
public class PostulantPublicationService {

    @Autowired
    private PostulantPublicationRepository postulantPublicationRepository;

    public List<PostulantPublication> getAll(){
        return postulantPublicationRepository.findAll();
    }

    public void store(PostulantPublication postulantPublication){
        postulantPublicationRepository.save(postulantPublication);
    }
}
