package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.Job;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.compositeKeys.CandidateId;
import com.bolsaTrabajo.model.jobInfo.Candidate;
import com.bolsaTrabajo.repositories.CandidateRepository;
import com.bolsaTrabajo.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mvip on 06-14-17.
 */
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public Candidate getJobOfPostulant(CandidateId candidateId) {
        return candidateRepository.findByPrimaryKey(candidateId);
    }

    @Override
    public List<Candidate> getJobForPostulant(Postulant postulant) {
        return candidateRepository.findByPrimaryKey_Postulant(postulant);
    }

    @Override
    public List<Candidate> getPostulantForJob(Job job) {
        return candidateRepository.findByPrimaryKey_Job(job);
    }

}
