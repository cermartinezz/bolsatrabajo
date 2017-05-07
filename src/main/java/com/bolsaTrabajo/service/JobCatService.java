package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.JobCat;
import com.bolsaTrabajo.repositories.JobCatRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by keepercito on 05-01-17.
 */
public class JobCatService {

    @Autowired
    private JobCatRepository jobCatRepository;

    public List<JobCat> getAllJobs(){
        return jobCatRepository.findAll();
    }

    public JobCat getJob(long id){
        return jobCatRepository.findById(id);
    }

    public JobCat getJob(String nom){
        return jobCatRepository.findByPuesto(nom);
    }

    public void saveJob(JobCat c){
        jobCatRepository.save(c);
    }

    public void deleteJob(JobCat c){ jobCatRepository.delete(c);}
}
