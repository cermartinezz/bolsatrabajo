package com.bolsaTrabajo.model;

import org.hibernate.validator.constraints.NotEmpty;

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
    @Column(name = "company_name")
    private String companyName;

    /*@NotNull
    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private List<WorkExperience> experiencia;*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotEmpty(message = "*Ingrese un nombre para la empresa")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String name) {
        this.companyName = name;
    }

    /*public List<WorkExperience> getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(List<WorkExperience> experiencia) {
        this.experiencia = experiencia;
    }*/
}
