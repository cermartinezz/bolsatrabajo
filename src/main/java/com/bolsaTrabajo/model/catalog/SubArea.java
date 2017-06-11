package com.bolsaTrabajo.model.catalog;

import com.bolsaTrabajo.model.Exam;
import com.bolsaTrabajo.model.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "CA_SUBAREA", //Usar el mismo nombre que el procedimiento almacenado
                procedureName = "CA_SUBAREA",           ////Usar el mismo nombre que el procedimiento almacenado
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "SA_ID", type = Long.class),          //Estos parametros los
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "SA_ID_AREA", type = Long.class),     //llamo igual que en el
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "SA_NOMBRE", type = String.class),    //procedimiento
                })
})
public class SubArea {
    private long id;
    private String nombre;
    private Area area;
    private Set<Question> questions;
    private Set<Exam> exams;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @ManyToOne
    @JsonIgnore
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subArea", cascade = CascadeType.REMOVE)
    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subArea", cascade = CascadeType.REMOVE)
    public Set<Exam> getExams() {
        return exams;
    }

    public void setExams(Set<Exam> exams) {
        this.exams = exams;
    }

    @Override
    public String toString() {
        return "SubArea{" +
                "id=" + id +
                ", area='" + area +
                ", nombre='" + nombre +
                '}';
    }
}
