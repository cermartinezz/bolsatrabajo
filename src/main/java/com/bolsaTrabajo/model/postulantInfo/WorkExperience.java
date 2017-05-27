package com.bolsaTrabajo.model.postulantInfo;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.CompanyCat;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by keepercito on 04-25-17.
 */

@Entity
@Table(name = "work_exp")
public class WorkExperience implements Serializable{

    private Postulant postulant;
    private CompanyCat company;
    @Column(name = "start_at")
    private int inicio;
    @Column(name = "finish_at")
    private int fin;

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
    public CompanyCat getCompany() {
        return company;
    }

    public void setCompany(CompanyCat companyCat) {
        this.company = companyCat;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }
}
