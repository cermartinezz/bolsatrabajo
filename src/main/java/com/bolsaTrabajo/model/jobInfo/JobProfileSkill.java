package com.bolsaTrabajo.model.jobInfo;

import com.bolsaTrabajo.model.catalog.Skill;
import com.bolsaTrabajo.model.compositeKeys.JobProfileSkillId;
import com.bolsaTrabajo.service.JobProfileService;
import com.bolsaTrabajo.service.JobProfileSkillService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "job_profiles_skills")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.JobProfile", joinColumns = @JoinColumn(name = "id")),
        @AssociationOverride(name = "primaryKey.Skill", joinColumns = @JoinColumn(name = "skill_id")),
})
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "SP_CREAR_JOB_PRO_SKILL",
                procedureName = "SP_CREAR_JOB_PRO_SKILL",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ID_HABILIDAD", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "NOMBRE_HABILIDAD", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ID_PERFIL", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ID_CATEGORIA", type = Integer.class),
                })
})
public class JobProfileSkill {

    @Autowired
    private JobProfileService jobProfileService;

    @Autowired
    private JobProfileSkillService jobProfileSkillService;

    @Autowired
    public JobProfileSkill(JobProfileService jobProfileService) {
        this.jobProfileService = jobProfileService;
    }

    public JobProfileSkill() {
    }

    public JobProfileSkillId primaryKey = new JobProfileSkillId();

    @EmbeddedId
    public JobProfileSkillId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(JobProfileSkillId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    @JsonIgnore
    public JobProfile getJobProfile() {
        return primaryKey.getJobProfile();
    }

    @Transient
    @JsonIgnore
    public void setJobProfile(JobProfile owner) {
        primaryKey.setJobProfile(owner);
    }

    @Transient
    public Skill getSkill() {
        return primaryKey.getSkill();
    }

    @Transient
    public void setSkill(Skill dependent) {
        primaryKey.setSkill(dependent);
    }


    public void save(JobProfileSkill jobProfileSkill, Integer id_profile) {
        JobProfile profile = jobProfileService.findById(id_profile);
        jobProfileSkill.setJobProfile(profile);
        jobProfileSkillService.save(jobProfileSkill);
    }
}
