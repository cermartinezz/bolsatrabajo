package com.bolsaTrabajo.model.catalog;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mvip on 04-05-17.
 */
@Entity
@Table(name = "skillCategory")
public class SkillCategory {

    private int id;
    private String titulo;
    private String codigo;
    private List<Skill> skills;

    public SkillCategory() {
        super();
    }

    public SkillCategory(int id) {
       this.id = id;
    }

    public SkillCategory(String titulo, String codigo) {
        this.titulo = titulo;
        this.codigo = codigo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "skillCategory_id")
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

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skillCategory")
    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
