package com.bolsaTrabajo.model.compositeKeys;

import com.bolsaTrabajo.model.catalog.JobCat;
import com.bolsaTrabajo.model.jobInfo.JobProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class WorkExperienceProfileId implements Serializable{

    private JobProfile jobProfile;
    private JobCat  job;

    @ManyToOne
    @JsonIgnore
    public JobProfile getJobProfile() {
        return jobProfile;
    }

    public void setJobProfile(JobProfile jobProfile) {
        this.jobProfile = jobProfile;
    }

    @ManyToOne
    @JsonIgnore
    public JobCat getJob() {
        return job;
    }

    public void setJob(JobCat job) {
        this.job = job;
    }
}
