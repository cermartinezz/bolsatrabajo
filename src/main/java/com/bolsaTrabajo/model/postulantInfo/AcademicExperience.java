package com.bolsaTrabajo.model.postulantInfo;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.AcademicTitleCat;
import com.bolsaTrabajo.model.catalog.Institution;
import com.bolsaTrabajo.model.compositeKeys.AcademicExperienceID;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by keepercito on 05-22-17.
 */

@Entity
@Table(name = "acad_exp")
@AssociationOverrides({
        @AssociationOverride(name = "pk.postulant",
                joinColumns = @JoinColumn(name = "postulant_id")),
        @AssociationOverride(name = "pk.institution",
                joinColumns = @JoinColumn(name = "institution_id")),
        @AssociationOverride(name = "pk.title",
                joinColumns = @JoinColumn(name = "title_id"))})
public class AcademicExperience implements Serializable {

    private AcademicExperienceID pk = new AcademicExperienceID();
    private int graduacion;

    @EmbeddedId
    public AcademicExperienceID getPk() {
        return pk;
    }

    public void setPk(AcademicExperienceID pk) {
        this.pk = pk;
    }

    public int getAñoGraduacion() {
        return graduacion;
    }

    public void setAñoGraduacion(int añoGraduacion) {
        this.graduacion = añoGraduacion;
    }

    @Transient
    public AcademicTitleCat getTitle() {
        return getPk().getTitle();
    }

    public void setTitle(AcademicTitleCat title) {
        getPk().setTitle(title);
    }

    @Transient
    @JsonIgnore
    public Postulant getPostulant(){
        return getPk().getPostulant();
    }

    public void setPostulant(Postulant p){getPk().setPostulant(p);}

    @Transient
    public Institution getInstitution(){
        return getPk().getInstitution();
    }

    public void setInstitution(Institution ins){getPk().setInstitution(ins);}

    @Column(name = "graduation")
    public int getGraduacion() {
        return graduacion;
    }

    public void setGraduacion(int graduacion) {
        this.graduacion = graduacion;
    }
}
