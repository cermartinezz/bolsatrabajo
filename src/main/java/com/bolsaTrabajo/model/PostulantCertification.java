package com.bolsaTrabajo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="postulants_certifications")
public class PostulantCertification implements Serializable {

    private Integer id;
    private Postulant postulant;
    private Certification certification;
    private Date expiration_date;
    private Integer certificationCode;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "postulant_id")
    public Postulant getPostulant() {
        return postulant;
    }

    public void setPostulant(Postulant postulant) {
        this.postulant = postulant;
    }

    @ManyToOne
    @JoinColumn(name = "certification_id")
    public Certification getCertification() {
        return certification;
    }

    public void setCertification(Certification certification) {
        this.certification = certification;
    }

    @Column(name = "published_date")
    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public Integer getCertificationCode() {
        return certificationCode;
    }

    public void setCertificationCode(Integer certificationCode) {
        this.certificationCode = certificationCode;
    }
}
