package com.bolsaTrabajo.model;

import javax.persistence.*;

/**
 * Created by mvip on 04-05-17.
 */
@Entity
@Table(name = "skill")
public class Skill {
    private int id;
    private String titulo;
    private String codigo;
    private SkillCategory skillCategory;

    public Skill() {
    }

    public Skill(int id, String titulo, String codigo, SkillCategory skillCategory) {
        this.id = id;
        this.titulo = titulo;
        this.codigo = codigo;
        this.skillCategory = skillCategory;
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
}
