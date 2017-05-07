package com.bolsaTrabajo.model;

import javax.persistence.*;

/**
 * Created by keepercito on 05-01-17.
 */

@Entity
@Table(name = "job")
public class JobCat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "puesto", unique = true, nullable = false)
    private String puesto;

    public JobCat() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}
