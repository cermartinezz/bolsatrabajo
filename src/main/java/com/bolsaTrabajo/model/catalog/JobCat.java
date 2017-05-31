package com.bolsaTrabajo.model.catalog;

import com.bolsaTrabajo.model.postulantInfo.WorkExperience;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by keepercito on 05-01-17.
 */

@Entity
@Table(name = "job")
public class JobCat {

    private long id;
    private String puesto;
    private Set<WorkExperience> workExperience;

    public JobCat() {
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
}
