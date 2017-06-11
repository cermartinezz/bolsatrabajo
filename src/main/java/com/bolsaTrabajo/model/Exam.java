package com.bolsaTrabajo.model;

import com.bolsaTrabajo.model.catalog.SubArea;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Exam {
    private long id;
    private String titulo;
    private int publicado; //1 - Si, 0 - No
    private Date fecha;
    private SubArea subArea;
    private Set<ExamResult> examResults;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getPublicado() {
        return publicado;
    }

    public void setPublicado(int publicado) {
        this.publicado = publicado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @ManyToOne
    @JsonIgnore
    public SubArea getSubArea() {
        return subArea;
    }

    public void setSubArea(SubArea subArea) {
        this.subArea = subArea;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "exam", cascade = CascadeType.REMOVE)
    public Set<ExamResult> getExamResults() {
        return examResults;
    }

    public void setExamResults(Set<ExamResult> examResults) {
        this.examResults = examResults;
    }

    public void publicar(){this.publicado = 1;}

    public void desPublicar(){this.publicado = 0;}
}
