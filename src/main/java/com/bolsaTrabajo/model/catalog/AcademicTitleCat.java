package com.bolsaTrabajo.model.catalog;

import com.bolsaTrabajo.model.jobInfo.AcademicExperienceProfile;
import com.bolsaTrabajo.model.postulantInfo.AcademicExperience;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "academic_titles")
public class AcademicTitleCat {

    private long id;
    private String titulo;
    private Set<AcademicExperience> academicExperienceSet;
    private Set<AcademicExperienceProfile> academicExperienceProfile;

    public AcademicTitleCat() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "title_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "title", unique = true, nullable = false)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @OneToMany(mappedBy = "pk.title", fetch = FetchType.LAZY)
    @JsonIgnore
    public Set<AcademicExperience> getAcademicExperienceSet() {
        return academicExperienceSet;
    }

    public void setAcademicExperienceSet(Set<AcademicExperience> academicExperienceSet) {
        this.academicExperienceSet = academicExperienceSet;
    }

    @OneToMany(mappedBy = "primaryKey.title",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    public Set<AcademicExperienceProfile> getAcademicExperienceProfile() {
        return academicExperienceProfile;
    }

    public void setAcademicExperienceProfile(Set<AcademicExperienceProfile> academicExperienceProfile) {
        this.academicExperienceProfile = academicExperienceProfile;
    }
}
