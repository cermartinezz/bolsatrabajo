package com.bolsaTrabajo.model.compositeKeys;

import com.bolsaTrabajo.model.catalog.Skill;
import com.bolsaTrabajo.model.jobInfo.JobProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class JobProfileSkillId implements Serializable {

    private JobProfile jobProfile;
    private Skill skill;

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
    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}