package com.bolsaTrabajo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "CA_CUESTIONARIO", //Usar el mismo nombre que el procedimiento almacenado
                procedureName = "CA_CUESTIONARIO",           ////Usar el mismo nombre que el procedimiento almacenado
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "CU_ID", type = Long.class),          //Estos parametros los
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "CU_ID_EXAM", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "CU_ID_QUESTION", type = Long.class),//llamo igual que en el
                })
})
public class Questionary {
    private long id;
    private Exam exam;
    private Question question;

    public Questionary(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JsonIgnore
    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @ManyToOne
    @JsonIgnore
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
