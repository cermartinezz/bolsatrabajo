package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Job;
import com.bolsaTrabajo.model.compositeKeys.CandidateId;
import com.bolsaTrabajo.model.jobInfo.Candidate;

import java.util.List;

/**
 * Created by mvip on 06-14-17.
 */
public interface CandidateService {

    Candidate getJobOfPostulant(CandidateId candidateId);
    //List<Candidate> getCandidatesOfJob(Job job);
}
