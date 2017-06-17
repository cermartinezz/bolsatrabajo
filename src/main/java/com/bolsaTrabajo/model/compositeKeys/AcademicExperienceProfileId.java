package com.bolsaTrabajo.model.compositeKeys;

import com.bolsaTrabajo.model.catalog.AcademicTitleCat;
import com.bolsaTrabajo.model.jobInfo.JobProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class AcademicExperienceProfileId implements Serializable{

    private JobProfile jobProfile;
    private AcademicTitleCat title;

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
    public AcademicTitleCat getTitle() {
        return title;
    }

    public void setTitle(AcademicTitleCat title) {
        this.title = title;
    }
}
