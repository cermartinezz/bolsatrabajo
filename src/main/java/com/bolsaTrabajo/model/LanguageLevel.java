package com.bolsaTrabajo.model;

import com.bolsaTrabajo.model.postulantInfo.PostulantLanguage;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mvip on 05-26-17.
 */
@Entity
@Table(name="languageLevel")
public class LanguageLevel {
    private int id;
    private String titulo;
    private String codigo;
    private PostulantLanguage postulantLanguage;

    public LanguageLevel(){
        super();
    }

    public LanguageLevel(int id) {
        this.id = id;
    }

    public LanguageLevel(String titulo, String codigo){
        this.titulo = titulo;
        this.codigo = codigo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "languageLevel_id")
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

    @OneToOne(mappedBy = "languageLevel")
    @JsonIgnore
    public PostulantLanguage getPostulantLanguage() {
        return postulantLanguage;
    }

    public void setPostulantLanguage(PostulantLanguage postulantLanguage) {
        this.postulantLanguage = postulantLanguage;
    }
}
