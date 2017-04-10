package com.bolsaTrabajo.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="certifications")
public class Certification implements Serializable{

    private int id;
    private String certificationCode;
    private String certificationTitle;
    private Institution institution;

    public Certification() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "certification_id")
    public int getCertificationId() {
        return id;
    }

    public void setCertificationId(int certificationId) {
        this.id = certificationId;
    }

    @Column(name="certification_code")
    public String getCertificationCode() {
        return certificationCode;
    }

    public void setCertificationCode(String certificationCode) {
        this.certificationCode = certificationCode;
    }

    @Column(name="certification_title")
    public String getCertificationTitle() {
        return certificationTitle;
    }

    public void setCertificationTitle(String certificationTitle) {
        this.certificationTitle = certificationTitle;
    }

    @ManyToOne
    @JoinColumn(name = "institution_id")
    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    @Override
    public String toString() {
        return "Certification{" +
                "certificationId=" + id +
                ", certificationCode='" + certificationCode + '\'' +
                ", certificationTitle='" + certificationTitle + '\'' +
                ", institution=" + institution +
                '}';
    }
}
