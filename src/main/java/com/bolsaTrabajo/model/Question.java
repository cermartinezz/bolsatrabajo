package com.bolsaTrabajo.model;

import com.bolsaTrabajo.model.catalog.SubArea;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "CA_PREGUNTA",
                procedureName = "CA_PREGUNTA",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Q_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Q_D1", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Q_D2", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Q_D3", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Q_D4", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Q_ENUNCIADO", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Q_PESO", type = int.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Q_RESPUESTA", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Q_SUBAREA_ID", type = Long.class),
                })
})
public class Question {
    private long id;
    private String enunciado;
    private String respuesta;
    private String d1;
    private String d2;
    private String d3;
    private String d4;
    private int peso;
    private SubArea subArea;
    private Set<Questionary> questionaries;

    public Question(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta.toLowerCase();
    }

    public String getD1() {
        return d1;
    }

    public void setD1(String d1) {
        this.d1 = d1.toLowerCase();
    }

    public String getD2() {
        return d2;
    }

    public void setD2(String d2) {
        this.d2 = d2.toLowerCase();
    }

    public String getD3() {
        return d3;
    }

    public void setD3(String d3) {
        this.d3 = d3.toLowerCase();
    }

    public String getD4() {
        return d4;
    }

    public void setD4(String d4) {
        this.d4 = d4.toLowerCase();
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @ManyToOne
    @JsonIgnore
    public SubArea getSubArea() {
        return subArea;
    }

    public void setSubArea(SubArea subArea) {
        this.subArea = subArea;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.REMOVE)
    @JsonIgnore
    public Set<Questionary> getQuestionaries() {
        return questionaries;
    }

    public void setQuestionaries(Set<Questionary> questionaries) {
        this.questionaries = questionaries;
    }
}
