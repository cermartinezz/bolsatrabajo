package com.bolsaTrabajo.model.compositeKeys;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.Certification;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class PostulantCertificationId implements Serializable {


    private Postulant postulant;
    private Certification certification;
    private String code;

    @ManyToOne
    public Postulant getPostulant() {
        return postulant;
    }

    public void setPostulant(Postulant postulant) {
        this.postulant = postulant;
    }

    @ManyToOne
    public Certification getCertification() {
        return certification;
    }

    public void setCertification(Certification certification) {
        this.certification = certification;
    }

    @Column(name="code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
