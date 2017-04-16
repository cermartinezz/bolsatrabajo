package com.bolsaTrabajo.model;

import com.bolsaTrabajo.util.InstitutionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="institution")
public class Institution {
    private int id;
    private String institutionCode;
    private String institutionName;
    private InstitutionType institutionType;
    private Set<Certification> certifications;

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
