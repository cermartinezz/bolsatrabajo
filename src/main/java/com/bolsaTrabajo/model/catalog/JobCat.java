package com.bolsaTrabajo.model.catalog;

import com.bolsaTrabajo.model.jobInfo.WorkExperienceProfile;
import com.bolsaTrabajo.model.postulantInfo.WorkExperience;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by keepercito on 05-01-17.
 */
@Entity
@Table(name = "job")
public class JobCat implements Serializable{

    private long id;
    private String puesto;
    private Set<WorkExperience> workExperience;
    private Set<WorkExperienceProfile> workExperienceProfiles;

    public JobCat() {
    }

    public JobCat(long id) {
        this.id = id;
    }

    public JobCat(String puesto) {
        this.puesto = puesto;
    }

    public JobCat(long id, String puesto) {
        this.id = id;
        this.puesto = puesto;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "puesto", unique = true, nullable = false)
    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @OneToMany(mappedBy = "jobCat", cascade = CascadeType.ALL)
    public Set<WorkExperience> getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(Set<WorkExperience> workExperience) {
        this.workExperience = workExperience;
    }

    @OneToMany( mappedBy = "primaryKey.job",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    @JsonIgnore
    public Set<WorkExperienceProfile> getWorkExperienceProfiles() {
        return workExperienceProfiles;
    }

    public void setWorkExperienceProfiles(Set<WorkExperienceProfile> workExperienceProfiles) {
        this.workExperienceProfiles = workExperienceProfiles;
    }
}
