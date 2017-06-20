package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.postulantInfo.Award;
import com.bolsaTrabajo.repositories.AwardRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by or.merino on 22/05/2017.
 */
public class AwardService {
    @Autowired
    AwardRepository awardRepository;
    public Award findByNombre(String name){ return awardRepository.findByNombre(name);}
    public void save(Award award){ awardRepository.save(award); }
    public Award findById(long id){ return awardRepository.findById(id); }
    public void deleteById(long id){ awardRepository.deleteById(id);}
}
