package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Job;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.compositeKeys.CandidateId;
import com.bolsaTrabajo.model.jobInfo.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("candidateRepository")
public interface CandidateRepository extends JpaRepository<Candidate,Integer>{

    Candidate findByPrimaryKey(CandidateId candidateId);
    List<Candidate> findByPrimaryKey_Postulant(Postulant postulant);
    List<Candidate> findByPrimaryKey_Job(Job job);
}
