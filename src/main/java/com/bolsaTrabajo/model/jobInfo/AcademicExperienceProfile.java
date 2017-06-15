package com.bolsaTrabajo.model.jobInfo;

import com.bolsaTrabajo.model.catalog.AcademicTitleCat;
import com.bolsaTrabajo.model.compositeKeys.AcademicExperienceProfileId;
import com.bolsaTrabajo.service.AcademicExperienceProfileService;
import com.bolsaTrabajo.service.JobProfileService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "academic_experiences_profiles")
@AssociationOverrides({
        @AssociationOverride(name="primaryKey.jobProfile", joinColumns = @JoinColumn(name = "id")),
        @AssociationOverride(name="primaryKey.title", joinColumns = @JoinColumn(name = "title_id")),
})
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "SP_CREAR_EXP_ACADEMICA_PERFIL",
        procedureName = "SP_CREAR_EXP_ACADEMICA_PERFIL",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "ID_PERFIL",      type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "ID_TITULO",      type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "NOMBRE_TITULO",  type = String.class),
        })
})
public class AcademicExperienceProfile {

    @Autowired
    JobProfileService jobProfileService;

    @Autowired
    AcademicExperienceProfileService academicExperienceProfileService;

    public AcademicExperienceProfile() {
    }
    @Autowired
    public AcademicExperienceProfile(JobProfileService jobProfileService) {
        this.jobProfileService = jobProfileService;
    }

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


    public void save(AcademicExperienceProfile academicExperienceProfile, Integer profile_id){
        JobProfile profile = jobProfileService.findById(profile_id);

        academicExperienceProfile.setJobProfile(profile);

        academicExperienceProfileService.save(academicExperienceProfile);

    }
}
