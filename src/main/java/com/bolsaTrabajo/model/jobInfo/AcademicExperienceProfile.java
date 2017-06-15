package com.bolsaTrabajo.model.jobInfo;

import com.bolsaTrabajo.model.catalog.AcademicTitleCat;
import com.bolsaTrabajo.model.compositeKeys.AcademicExperienceProfileId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "academic_experiences_profiles")
public class AcademicExperienceProfile {

    public AcademicExperienceProfileId primaryKey = new AcademicExperienceProfileId();

    @EmbeddedId
    public AcademicExperienceProfileId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(AcademicExperienceProfileId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    @JsonIgnore
    public JobProfile getJobProfile(){
        return primaryKey.getJobProfile();
    }

    @Transient
    public void setJobProfile(JobProfile jobProfile){
        primaryKey.setJobProfile(jobProfile);
    }

    @Transient
    public AcademicTitleCat getAcademicTitle(){
        return primaryKey.getTitle();
    }

    @Transient
    public void setAcademicTitleCat(AcademicTitleCat academicTitleCat){
        primaryKey.setTitle(academicTitleCat);
    }

}
