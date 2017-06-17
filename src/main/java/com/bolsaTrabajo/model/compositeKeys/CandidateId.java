package com.bolsaTrabajo.model.compositeKeys;

import com.bolsaTrabajo.model.Job;
import com.bolsaTrabajo.model.Postulant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by mvip on 06-14-17.
 */
@Embeddable
public class CandidateId implements Serializable{
    private Postulant postulant;
    private Job job;

    public CandidateId(Postulant postulant, Job job) {
        this.postulant = postulant;
        this.job = job;
    }

    public CandidateId() {
    }

    @ManyToOne
    public Postulant getPostulant() {
        return postulant;
    }

    public void setPostulant(Postulant postulant) {
        this.postulant = postulant;
    }

    @ManyToOne
    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "CandidateId{" +
                "postulant=" + postulant +
                ", job=" + job +
                '}';
    }
}
