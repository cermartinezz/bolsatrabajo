package com.bolsaTrabajo.service.implementations;


import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.model.Job;
import com.bolsaTrabajo.repositories.JobRepository;
import com.bolsaTrabajo.repositories.RoleRepository;
import com.bolsaTrabajo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;


public class JobServiceImpl implements JobService{
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(Job job) {
        jobRepository.save(job);
    }

    @Override
    public List<Job> findAllByCompany(Company company) {
        return jobRepository.findAllByCompany(company);
    }

    @Override
    public Job findByCodJ(String cod) {
        return jobRepository.findByCodJ(cod);
    }

    @Override
    public void updateJob(Job job) {
        jobRepository.save(job);
    }


    @Override
    public Job findById(long id){return jobRepository.findById(id);}
    @Override
    public void deleteById(long id){ jobRepository.deleteById(id);}
}
