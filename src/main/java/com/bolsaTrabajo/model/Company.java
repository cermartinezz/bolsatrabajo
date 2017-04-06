package com.bolsaTrabajo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by enan0 on 6/4/2017.
 */

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "comp_name")
    private String comp_name;

    @NotNull
    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private List<WorkExperience> experiencia;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComp_name() {
        return comp_name;
    }

    public void setComp_name(String comp_name) {
        this.comp_name = comp_name;
    }

    public List<WorkExperience> getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(List<WorkExperience> experiencia) {
        this.experiencia = experiencia;
    }
}
