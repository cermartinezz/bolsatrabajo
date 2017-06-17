package com.bolsaTrabajo.model.jobInfo;

import com.bolsaTrabajo.model.catalog.Language;
import com.bolsaTrabajo.model.catalog.LanguageLevel;
import com.bolsaTrabajo.model.compositeKeys.JobProfileLanguageId;
import com.bolsaTrabajo.service.JobProfileLanguageService;
import com.bolsaTrabajo.service.JobProfileService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "job_profiles_languages")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.jobProfile", joinColumns = @JoinColumn(name = "id")),
        @AssociationOverride(name = "primaryKey.language", joinColumns = @JoinColumn(name = "languaje_id")),
        @AssociationOverride(name = "primaryKey.languageLevel", joinColumns = @JoinColumn(name = "languageLevel_id")),
})
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "SP_CREAR_JOB_PRO_LANG",
                procedureName = "SP_CREAR_JOB_PRO_LANG",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ID_PERFIL", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ID_NIVEL", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ID_LENGUAJE", type = Integer.class),
                })
})
public class JobProfileLanguage {

    @Autowired
    JobProfileLanguageService jobProfileLanguageService;

    @Autowired
    JobProfileService jobProfileService;

    @Autowired
    public JobProfileLanguage(JobProfileLanguageService jobProfileLanguageService,JobProfileService jobProfileService) {
        this.jobProfileLanguageService = jobProfileLanguageService;
        this.jobProfileService = jobProfileService;
    }

    public JobProfileLanguage() {
    }

    public JobProfileLanguageId primaryKey = new JobProfileLanguageId();

    @EmbeddedId
    public JobProfileLanguageId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(JobProfileLanguageId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    @JsonIgnore
    public JobProfile getJobProfile() {
        return primaryKey.getJobProfile();
    }

    @Transient
    public void setJobProfile(JobProfile owner) {
        primaryKey.setJobProfile(owner);
    }

    @Transient
    public Language getLanguage() {
        return primaryKey.getLanguage();
    }

    @Transient
    public void setLanguage(Language dependent) {
        primaryKey.setLanguage(dependent);
    }

    @Transient
    public LanguageLevel getLanguageLevel() {
        return primaryKey.getLanguageLevel();
    }

    @Transient
    public void setLanguageLevel(LanguageLevel level) {
        primaryKey.setLanguageLevel(level);
    }


    public void save(JobProfileLanguage jobProfileLanguage, Integer id_profile) {
        JobProfile profile = jobProfileService.findById(id_profile);
        jobProfileLanguage.setJobProfile(profile);

        this.jobProfileLanguageService.save(jobProfileLanguage);
    }
}
