package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.compositeKeys.CandidateId;
import com.bolsaTrabajo.model.jobInfo.Candidate;

/**
 * Created by mvip on 06-14-17.
 */
public interface CandidateService {

    Candidate getJobOfPostulant(CandidateId candidateId);

}
