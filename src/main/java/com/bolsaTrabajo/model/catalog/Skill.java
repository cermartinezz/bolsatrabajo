package com.bolsaTrabajo.model.catalog;

import com.bolsaTrabajo.model.postulantInfo.PostulantSkill;

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

    public Skill() {
        super();
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

    @OneToMany( mappedBy = "skill",
                cascade = CascadeType.ALL,
                fetch = FetchType.EAGER)
    public Set<PostulantSkill> getPostulantSkills() {
        return postulantSkills;
    }

    public void setPostulantSkills(Set<PostulantSkill> postulantSkills) {
        this.postulantSkills = postulantSkills;
    }
}
