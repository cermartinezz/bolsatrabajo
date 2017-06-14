package com.bolsaTrabajo.model.jobInfo;

import com.bolsaTrabajo.model.catalog.JobCat;
import com.bolsaTrabajo.model.compositeKeys.WorkExperienceProfileId;
import com.bolsaTrabajo.service.JobProfileService;
import com.bolsaTrabajo.service.WorkExperienceProfileService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "work_experiences_profiles")
@AssociationOverrides({
        @AssociationOverride(name="primaryKey.jobProfile", joinColumns = @JoinColumn(name = "id")),
        @AssociationOverride(name="primaryKey.job", joinColumns = @JoinColumn(name = "job_id")),
})
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "SP_CREAR_EXPERI_LABORAL_PREFIL",
                procedureName = "SP_CREAR_EXPERI_LABORAL_PREFIL",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN,name="ID_PROFILE",type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN,name="ID_PUESTO",type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN,name="AÑOS",type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN,name="NOMBRE_PUESTO",type = String.class),
                })
})
public class WorkExperienceProfile {

    private WorkExperienceProfileId primaryKey = new WorkExperienceProfileId();
    private Integer yearOfExperience;

    @Autowired
    private WorkExperienceProfileService workExperienceProfileService;

    @Autowired
    private JobProfileService jobProfileService;
    @Autowired
    public WorkExperienceProfile(WorkExperienceProfileService workExperienceProfileService) {
        this.workExperienceProfileService = workExperienceProfileService;
    }

    public WorkExperienceProfile() {
    }

    @EmbeddedId
    public WorkExperienceProfileId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(WorkExperienceProfileId primaryKey) {
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
    public JobCat getJobCat(){
        return primaryKey.getJob();
    }

    @Transient
    public void setJobCat(JobCat jobCat){
        primaryKey.setJob(jobCat);
    }

    public Integer getYearOfExperience() {
        return yearOfExperience;
    }

    public void setYearOfExperience(Integer yearOfExperience) {
        this.yearOfExperience = yearOfExperience;
    }

    public void save(WorkExperienceProfile workExperienceProfile, Integer jobcat_id){

        JobProfile profile = jobProfileService.findById(jobcat_id);

        WorkExperienceProfileId workExperienceProfileId = new WorkExperienceProfileId();
        workExperienceProfileId.setJob(workExperienceProfile.getJobCat());
        workExperienceProfileId.setJobProfile(profile);
        workExperienceProfile.setPrimaryKey(workExperienceProfileId);

        this.workExperienceProfileService.save(workExperienceProfile);
    }
}
