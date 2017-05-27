package com.bolsaTrabajo.model.catalog;

import com.bolsaTrabajo.model.postulantInfo.WorkExperience;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by enan0 on 6/4/2017.
 */

@Entity
@Table(name = "companies_cat")
public class CompanyCat {

    private Long id;
    private String companyName;
    private Set<WorkExperience> workExperiences;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_cat_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String name) {
        this.companyName = name;
    }

    @OneToMany(mappedBy = "pk.companyCat", fetch = FetchType.LAZY)
    public Set<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(Set<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }
}
