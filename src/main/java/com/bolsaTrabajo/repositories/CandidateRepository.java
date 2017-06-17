package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Job;
import com.bolsaTrabajo.model.compositeKeys.CandidateId;
import com.bolsaTrabajo.model.jobInfo.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mvip on 06-14-17.
 */
@Repository("candidateRepository")
public interface CandidateRepository extends JpaRepository<Candidate,Integer>{

    Candidate findByPrimaryKey(CandidateId candidateId);
    //List<Candidate> findByJob(Job job);
}
