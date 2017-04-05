package com.bolsaTrabajo.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="certifications")
public class Certification implements Serializable{

    private int certificationId;
    private String certificationCode;
    private String certificationTitle;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="certification_id")
    public int getCertificationId() {
        return certificationId;
    }

    public void setCertificationId(int certificationId) {
        this.certificationId = certificationId;
    }

    @Column(name="certification_code")
    @NotEmpty(message = "*Ingrese un codigo")
    public String getCertificationCode() {
        return certificationCode;
    }

    public void setCertificationCode(String certificationCode) {
        this.certificationCode = certificationCode;
    }

    @Column(name="certification_title")
    @NotEmpty(message = "*Ingrese un Titulo")
    public String getCertificationTitle() {
        return certificationTitle;
    }

    public void setCertificationTitle(String certificationTitle) {
        this.certificationTitle = certificationTitle;
    }

    @Override
    public String toString() {
        return "Certification{" +
                "certificationId=" + certificationId +
                ", certificationCode='" + certificationCode + '\'' +
                ", certificationTitle='" + certificationTitle + '\'' +
                '}';
    }
}
