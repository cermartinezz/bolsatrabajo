package com.bolsaTrabajo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by enan0 on 5/4/2017.
 */

@Entity
@Table(name = "academic_title")
public class AcademicTitle implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Size(max = 30)
    private String name;
    @NotNull
    private Institution institution;

    public AcademicTitle() {
    }

    @ManyToOne
    @JoinColumn(name = "intitution_id")
    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
