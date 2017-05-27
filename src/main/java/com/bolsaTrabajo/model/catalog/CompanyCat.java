package com.bolsaTrabajo.model.catalog;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by enan0 on 6/4/2017.
 */

@Entity
@Table(name = "companies")
public class CompanyCat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "company_name")
    private String companyName;

/*
    @OneToMany(mappedBy = "company")
    private Set<WorkExperience> workExperiences;
*/

/*
    public Set<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }
*/

/*
    public void setWorkExperiences(Set<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }
*/

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
}
