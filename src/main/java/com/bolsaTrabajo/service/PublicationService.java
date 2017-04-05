package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Publication;
import com.bolsaTrabajo.repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mvip on 04-04-17.
 */
public class PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    public List<Publication> getAllPublications(){
        return publicationRepository.findAll();
    }


    public Publication findPublicationByCodigo(String code){
        return publicationRepository.findByCodigo(code);
    }

    public void storePublication(Publication publication){
        publicationRepository.save(publication);
    }

    public void updatePublication(Publication publication) {
        storePublication(publication);
    }
}
