package com.bolsaTrabajo.model.jobInfo;

import com.bolsaTrabajo.model.catalog.JobCat;
import com.bolsaTrabajo.model.compositeKeys.WorkExperienceProfileId;

import javax.persistence.*;

@Entity
@Table(name = "work_experiences_profiles")
@AssociationOverrides({
        @AssociationOverride(name="primaryKey.jobProfile", joinColumns = @JoinColumn(name = "id")),
        @AssociationOverride(name="primaryKey.job", joinColumns = @JoinColumn(name = "job_id")),
})
public class WorkExperienceProfile {

    private WorkExperienceProfileId primaryKey = new WorkExperienceProfileId();
    private String name;
    private String description;
    private Integer años;

    @EmbeddedId
    public WorkExperienceProfileId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(WorkExperienceProfileId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public JobProfile getJobProfile(){
        return primaryKey.getJobProfile();
    }

    @Transient
    public void setJobProfile(JobProfile jobProfile){
        primaryKey.setJobProfile(jobProfile);
    }

    @Transient
    public JobCat getJobCat(){
        return primaryKey.getJob();
    }

    @Transient
    public void setJobCat(JobCat jobCat){
        primaryKey.setJob(jobCat);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAños() {
        return años;
    }

    public void setAños(Integer años) {
        this.años = años;
    }
}
