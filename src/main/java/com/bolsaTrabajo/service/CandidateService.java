package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Job;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.compositeKeys.CandidateId;
import com.bolsaTrabajo.model.jobInfo.Candidate;

import java.util.List;


public interface CandidateService {

    Candidate getJobOfPostulant(CandidateId candidateId);
    List<Candidate> getJobForPostulant(Postulant postulant);
    List<Candidate> getPostulantForJob(Job job);

}
