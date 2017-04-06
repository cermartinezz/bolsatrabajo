package com.bolsaTrabajo.model;

import com.bolsaTrabajo.util.InstitutionType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="institution")
public class Institution implements Serializable{
    private Long id;
    private String institutionCode;
    private String institutionName;
    private InstitutionType institutionType;
    @NotNull
    private List<AcademicExperience> experiencia;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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


    @OneToMany(mappedBy = "institution",cascade = CascadeType.ALL)
    public List<AcademicExperience> getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(List<AcademicExperience> experiencia) {
        this.experiencia = experiencia;
    }
}
