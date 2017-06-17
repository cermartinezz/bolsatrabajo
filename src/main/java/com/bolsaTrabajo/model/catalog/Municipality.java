package com.bolsaTrabajo.model.catalog;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mvip on 06-12-17.
 */
@Entity
@Table(name = "municipality")
public class Municipality implements Serializable{

    private int id;
    private String nombre;
    private Department department;

    public Municipality(String nombre,Department department) {
        this.nombre = nombre;
        this.department = department;
    }

    public Municipality() {
        super();
    }

    public Municipality(String nombre) {
        this.nombre = nombre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "municipality_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @ManyToOne
    @JoinColumn(name="department_id")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
