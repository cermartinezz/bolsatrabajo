package com.bolsaTrabajo.model.catalog;

import com.bolsaTrabajo.model.postulantInfo.PostulantCertification;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="certification")
public class Certification implements Serializable{

    private Integer id;
    private String certificationCode;
    private String certificationTitle;
    private Institution institution;
    private Set<PostulantCertification> postulantCertifications;

    public Certification() {
        super();
    }

    public Certification(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "certification_id")
    public Integer getCertificationId() {
        return id;
    }

    public void setCertificationId(Integer certificationId) {
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


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "institution_id")
    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    @OneToMany( mappedBy = "primaryKey.certification",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    @JsonIgnore
    public Set<PostulantCertification> getPostulantCertifications() {
        return postulantCertifications;
    }

    public void setPostulantCertifications(Set<PostulantCertification> postulantCertifications) {
        this.postulantCertifications = postulantCertifications;
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
