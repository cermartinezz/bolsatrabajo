package com.bolsaTrabajo.model;

import com.bolsaTrabajo.util.Gender;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Postulant extends User{

    private Long id;
    private String dui;
    private Date birthday;
    private String nit;
    private Gender gender;
    private Set<PostulantCertification> postulantCertifications;

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

    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @OneToMany(mappedBy = "postulant", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<PostulantCertification> getPostulantCertifications() {
        return postulantCertifications;
    }

    public void setPostulantCertifications(Set<PostulantCertification> postulantCertifications) {
        this.postulantCertifications = postulantCertifications;
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
