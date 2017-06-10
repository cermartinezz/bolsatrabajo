package com.bolsaTrabajo.model.postulantInfo;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.CompanyCat;
import com.bolsaTrabajo.model.catalog.JobCat;
import com.bolsaTrabajo.model.compositeKeys.WorkExperienceID;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by keepercito on 04-25-17.
 */

@Entity
@Table(name = "work_exp")
@AssociationOverrides({
        @AssociationOverride(name = "pk.postulant",
                joinColumns = @JoinColumn(name = "postulant_id")),
        @AssociationOverride(name = "pk.companyCat",
                joinColumns = @JoinColumn(name = "company_cat_id")),
        @AssociationOverride(name = "pk.jobCat",
                joinColumns = @JoinColumn(name = "job_id"))})
public class WorkExperience implements Serializable{

    private WorkExperienceID pk = new WorkExperienceID();
    private String fin;

    @EmbeddedId
    public WorkExperienceID getPk() {
        return pk;
    }

    public void setPk(WorkExperienceID id) {
        this.pk = id;
    }

    @Transient
    public CompanyCat getCompanyCat() {
        return getPk().getCompanyCat();
    }

    public void setCompanyCat(CompanyCat companyCat) {
        getPk().setCompanyCat(companyCat);
    }

    @Transient
    @JsonIgnore
    public Postulant getPostulant() {
        return getPk().getPostulant();
    }

    public void setPostulant(Postulant postulant) {
        getPk().setPostulant(postulant);
    }

    @Transient
    public String getInicio() {
        return getPk().getInicio();
    }

    public void setInicio(String inicio) {
        getPk().setInicio(inicio);
    }

    @Column(name = "finish_at")
    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }


    @Transient
    public JobCat getJobCat() {
        return getPk().getJobCat();
    }

    public void setJobCat(JobCat jobCat) {
        getPk().setJobCat(jobCat);
    }
}
