package com.bolsaTrabajo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by enan0 on 5/4/2017.
 */
@Entity
@Table(name = "wrk_exp")
public class WorkExperience implements Serializable{

    private Postulant postulant;
    private Company company;
    @NotNull
    @Column(name = "inicio")
    private int inicio;
    @NotNull
    @Column(name = "finalizacion")
    private int finalizacion;

    public WorkExperience() {
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

    @Id
    @ManyToOne
    @JoinColumn(name = "company_id")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFinalizacion() {
        return finalizacion;
    }

    public void setFinalizacion(int finalizacion) {
        this.finalizacion = finalizacion;
    }
}