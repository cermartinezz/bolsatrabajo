package com.bolsaTrabajo.model.compositeKeys;

import com.bolsaTrabajo.model.catalog.CompanyCat;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.JobCat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by keepercito on 05-13-17.
 */

@Embeddable
public class WorkExperienceID implements Serializable{


    private Postulant postulant;
    private CompanyCat companyCat;
    private JobCat jobCat;
    private String inicio;

    @Column(name = "start_at")
    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    @ManyToOne
    @JsonIgnore
    public JobCat getJobCat() {
        return jobCat;
    }

    public void setJobCat(JobCat jobCat) {
        this.jobCat = jobCat;
    }

    @ManyToOne
    public Postulant getPostulant() {
        return postulant;
    }

    public void setPostulant(Postulant postulant) {
        this.postulant = postulant;
    }

    @ManyToOne
    @JsonIgnore
    public CompanyCat getCompanyCat() {
        return companyCat;
    }

    public void setCompanyCat(CompanyCat companyCat) {
        this.companyCat = companyCat;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof WorkExperienceID)) return false;
        WorkExperienceID that = (WorkExperienceID) o;
        return Objects.equals(getCompanyCat(), that.getCompanyCat()) && Objects.equals(getPostulant(), that.getPostulant());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getCompanyCat(),getPostulant());
    }
}
