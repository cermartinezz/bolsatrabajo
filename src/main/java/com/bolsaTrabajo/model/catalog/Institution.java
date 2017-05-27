package com.bolsaTrabajo.model.catalog;

import com.bolsaTrabajo.model.postulantInfo.AcademicExperience;
import com.bolsaTrabajo.util.InstitutionType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="institution")
public class Institution {
    private int id;
    private String institutionCode;
    private String institutionName;
    private InstitutionType institutionType;
    private Set<Certification> certifications;
    private Set<AcademicExperience> academicExperiences;

    public Institution() {
        super();
    }

    public Institution(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    @Enumerated(EnumType.STRING)
    public InstitutionType getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(InstitutionType institutionType) {
        this.institutionType = institutionType;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Certification> getCertifications() {
        return certifications;
    }

    public void setCertifications(Set<Certification> certifications) {
        this.certifications = certifications;
    }

    @OneToMany(mappedBy = "pk.institution", fetch = FetchType.LAZY)
    public Set<AcademicExperience> getAcademicExperiences() {
        return academicExperiences;
    }

    public void setAcademicExperiences(Set<AcademicExperience> academicExperiences) {
        this.academicExperiences = academicExperiences;
    }

    @Override
    public String toString() {
        return "Institution{" +
                "id=" + id +
                ", institutionCode='" + institutionCode + '\'' +
                ", institutionName='" + institutionName + '\'' +
                ", institutionType=" + institutionType +
                '}';
    }
}
