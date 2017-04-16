package com.bolsaTrabajo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by enan0 on 5/4/2017.
 */

@Entity
@Table(name = "academic_exp")
public class AcademicExperience implements Serializable{

    private Postulant postulant;
    @NotNull
    @Column(name = "grad_yr")
    private int graduationYear;

    private Institution institution;

    public AcademicExperience() {
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "postulant_id")
    public Postulant getPostulant() {
        return postulant;
    }

    public void setPostulant(Postulant postulant) {
        this.postulant = postulant;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "institution_id")
    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
}
