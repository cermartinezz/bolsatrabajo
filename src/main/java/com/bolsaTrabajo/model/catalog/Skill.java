package com.bolsaTrabajo.model.catalog;

import com.bolsaTrabajo.model.jobInfo.JobProfileSkill;
import com.bolsaTrabajo.model.postulantInfo.PostulantSkill;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "skill")
public class Skill implements Serializable{

    private int id;
    private String titulo;
    private String codigo;
    private SkillCategory skillCategory;
    private Set<PostulantSkill> postulantSkills;
    private Set<JobProfileSkill> jobProfileSkills;

    public Skill() {
        super();
    }

    public Skill(String titulo, String codigo, SkillCategory skillCategory) {
        this.titulo = titulo;
        this.codigo = codigo;
        this.skillCategory = skillCategory;
    }

    public Skill(String codigo) {
        this.codigo = codigo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "skill_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "titulo", unique = true)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Column(name = "codigo", unique = true)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    @ManyToOne
    @JoinColumn(name = "skillCategory_id")
    public SkillCategory getSkillCategory() {
        return skillCategory;
    }

    public void setSkillCategory(SkillCategory skillCategory) {

        this.skillCategory = skillCategory;
    }

    @OneToMany( mappedBy = "primaryKey.skill",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    @JsonIgnore
    public Set<PostulantSkill> getPostulantSkills() {
        return postulantSkills;
    }

    public void setPostulantSkills(Set<PostulantSkill> postulantSkills) {
        this.postulantSkills = postulantSkills;
    }

    public Skill(Set<JobProfileSkill> jobProfileSkills) {
        this.jobProfileSkills = jobProfileSkills;
    }

    @OneToMany( mappedBy = "primaryKey.skill",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JsonIgnore
    public Set<JobProfileSkill> getJobProfileSkills() {
        return jobProfileSkills;
    }

    public void setJobProfileSkills(Set<JobProfileSkill> jobProfileSkills) {
        this.jobProfileSkills = jobProfileSkills;
    }
}
