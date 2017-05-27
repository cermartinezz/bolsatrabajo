package com.bolsaTrabajo.model.catalog;

import com.bolsaTrabajo.model.postulantInfo.AcademicExperience;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by keepercito on 05-21-17.
 */

@Entity
@Table(name = "academic_titles")
public class AcademicTitleCat {

    private Long id;
    private String titulo;
    private Set<AcademicExperience> academicExperienceSet;

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

    @OneToMany(mappedBy = "title", fetch = FetchType.LAZY)
    public Set<AcademicExperience> getAcademicExperienceSet() {
        return academicExperienceSet;
    }

    public void setAcademicExperienceSet(Set<AcademicExperience> academicExperienceSet) {
        this.academicExperienceSet = academicExperienceSet;
    }
}
