package com.bolsaTrabajo.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by mvip on 05-26-17.
 */
public class LanguageLevel {
    private int id;
    private String titulo;
    private String codigo;

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
}
