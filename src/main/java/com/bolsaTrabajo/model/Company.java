package com.bolsaTrabajo.model;

import javax.persistence.*;


@Entity
@Table(name = "company")
public class Company extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombreC;

    private String repreLegal;

    private String nitC;

    private String telefonoC;

    private String informacionC;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public String getRepreLegal() {
        return repreLegal;
    }

    public void setRepreLegal(String repreLegal) {
        this.repreLegal = repreLegal;
    }

    public String getNitC() {
        return nitC;
    }

    public void setNitC(String nitC) {
        this.nitC = nitC;
    }

    public String getTelefonoC() {
        return telefonoC;
    }

    public void setTelefonoC(String telefonoC) {
        this.telefonoC = telefonoC;
    }

    public String getInformacionC() {
        return informacionC;
    }

    public void setInformacionC(String informacionC) {
        this.informacionC = informacionC;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", nombreC='" + nombreC + '\'' +
                ", repreLegal=" + repreLegal +
                ", nitC='" + nitC + '\'' +
                ", telefonoC='" + telefonoC + '\'' +
                ", informacionC=" + informacionC +
                '}';
    }
}
