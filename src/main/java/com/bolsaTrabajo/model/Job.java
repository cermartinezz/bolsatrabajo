package com.bolsaTrabajo.model;


import com.bolsaTrabajo.model.catalog.Department;
import com.bolsaTrabajo.model.jobInfo.JobProfile;

import javax.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {

    private Long id;
    private String codJ;
    private String nombreJ;
    private float salarioJ;
    private  String descripcionJ;
    private String category;
    private Company company;
    private JobProfile jobProfile;
    private Department department;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_job")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNombreJ() {
        return nombreJ;
    }


    public void setNombreJ(String nombreJ) {
        this.nombreJ = nombreJ;
    }

    public float getSalarioJ() {
        return salarioJ;
    }

    public void setSalarioJ(float salarioJ) {
        this.salarioJ = salarioJ;
    }

    public String getDescripcionJ() {
        return descripcionJ;
    }

    public void setDescripcionJ(String descripcionJ) {
        this.descripcionJ = descripcionJ;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name="company_id")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getCodJ() {
        return codJ;
    }

    public void setCodJ(String codJ) {
        this.codJ = codJ;
    }

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name="job_profile_id")
    public JobProfile getJobProfile() {
        return jobProfile;
    }

    public void setJobProfile(JobProfile jobProfile) {
        this.jobProfile = jobProfile;
    }

    @ManyToOne
    @JoinColumn(name = "department_id")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", codJ='" + codJ + '\'' +
                ", nombreJ='" + nombreJ + '\'' +
                ", salarioJ=" + salarioJ +
                ", descripcionJ='" + descripcionJ + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
