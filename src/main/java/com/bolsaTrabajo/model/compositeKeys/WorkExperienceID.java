package com.bolsaTrabajo.model.compositeKeys;

import com.bolsaTrabajo.model.catalog.CompanyCat;
import com.bolsaTrabajo.model.Postulant;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by keepercito on 05-13-17.
 */

@Embeddable
public class WorkExperienceID implements Serializable{


    private Postulant postulant;
    private CompanyCat companyCat;

    @ManyToOne
    public Postulant getPostulant() {
        return postulant;
    }

    public void setPostulant(Postulant postulant) {
        this.postulant = postulant;
    }

    @ManyToOne
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
