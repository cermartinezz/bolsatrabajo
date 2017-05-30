package com.bolsaTrabajo.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mvip on 05-26-17.
 */
@Entity
@Table(name="languages")
public class Language implements Serializable{
    private int id;
    private String codigo;
    private String nombre;

    public Language(){
        super();
    }

    public Language(String codigo, String nombre){
        this.codigo = codigo;
        this.nombre = nombre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "language_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "code",unique = true)
    @NotEmpty(message ="*Por favor ingrese un código")
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "name")
    @NotEmpty(message ="*Por favor ingrese un nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
