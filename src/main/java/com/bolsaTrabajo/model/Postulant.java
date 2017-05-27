package com.bolsaTrabajo.model;

import com.bolsaTrabajo.model.postulantInfo.AcademicExperience;
import com.bolsaTrabajo.model.postulantInfo.PostulantCertification;
import com.bolsaTrabajo.model.postulantInfo.WorkExperience;
import com.bolsaTrabajo.util.Gender;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
public class Postulant extends User{

    private Long id;
    private String username;
    private String dui;
    private Date birthday;
    private String nit;
    private String nup;
    private String pasaporte;
    private String telefono;
    private String celular;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Set<PostulantCertification> postulantCertifications;

    private Set<WorkExperience> workExperiences;

    private Set<AcademicExperience> academicExperiences;

    public Postulant() {
        super();
    }

    public Postulant(String username) {
        this.username = username;
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "dui", unique = true)
    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "nit", unique = true)
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    @Column(name = "nup", unique = true)
    public String getNup() {
        return nup;
    }

    public void setNup(String nup) {
        this.nup = nup;
    }

    @Column(name = "passport", unique = true)
    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    @Column(name = "tel")
    @Size(max = 9)
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Size(max = 9)
    @Column(name = "cellphone")
    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }


    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @OneToMany(mappedBy = "postulant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<PostulantCertification> getPostulantCertifications() {
        return postulantCertifications;
    }

    public void setPostulantCertifications(Set<PostulantCertification> postulantCertifications) {
        this.postulantCertifications = postulantCertifications;
    }

    @OneToMany(mappedBy = "pk.postulant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(Set<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }

    @OneToMany(mappedBy = "pk.postulant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<AcademicExperience> getAcademicExperiences() {
        return academicExperiences;
    }

    public void setAcademicExperiences(Set<AcademicExperience> academicExperiences) {
        this.academicExperiences = academicExperiences;
    }

    @Override
    public String toString() {
        return "Postulant{" +
                "id=" + id +
                ", dui='" + dui + '\'' +
                ", birthday=" + birthday +
                ", nit='" + nit + '\'' +
                ", gender=" + gender +
                '}';
    }
}
