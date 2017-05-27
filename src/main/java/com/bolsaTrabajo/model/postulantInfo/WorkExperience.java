package com.bolsaTrabajo.model.postulantInfo;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.CompanyCat;
import com.bolsaTrabajo.model.catalog.JobCat;
import com.bolsaTrabajo.model.compositeKeys.WorkExperienceID;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by keepercito on 04-25-17.
 */

@Entity
@Table(name = "work_exp")
@AssociationOverrides({
        @AssociationOverride(name = "pk.postulant",
                joinColumns = @JoinColumn(name = "postulant_id")),
        @AssociationOverride(name = "pk.companyCat",
                joinColumns = @JoinColumn(name = "company_cat_id")) })
public class WorkExperience implements Serializable{

    private WorkExperienceID pk = new WorkExperienceID();
    private int inicio;
    private int fin;
    private JobCat jobCat;

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
    public Postulant getPostulant() {
        return getPk().getPostulant();
    }

    public void setPostulant(Postulant postulant) {
        getPk().setPostulant(postulant);
    }

    @Column(name = "start_at")
    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    @Column(name = "finish_at")
    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    @ManyToOne
    @JoinColumn(name = "job_id")
    public JobCat getJobCat() {
        return jobCat;
    }

    public void setJobCat(JobCat jobCat) {
        this.jobCat = jobCat;
    }
}
