package com.bolsaTrabajo.model;

import com.bolsaTrabajo.model.postulantInfo.*;
import com.bolsaTrabajo.util.Gender;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Postulant extends User{

    private Long id;
    private String username;
    private String dui;
    private Date birthday;
    private String nit;
    private Gender gender;
    private String cellphone;
    private String phonenumber;
    private String passport;
    private String nup;

    private Set<PostulantCertification> certifications;
    private Set<Recommendation> recommendations;
    private Set<PostulantSkill> skills;
    private Set<Award> awards;
    private Set<PostulantPublication> postulantPublications;
    private Set<PostulantLanguage> postulantLanguages;

    public Postulant() {super();}

    public Postulant(String username) {
        this.username = username;
    }


    @OneToMany(mappedBy = "postulant")
    private Set<WorkExperience> workExperiences;

    private Set<AcademicExperience> academicExperiences;



    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "dui")
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

    @Column(name = "nit")
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

    public String getNup() {
        return nup;
    }

    public void setNup(String nup) {
        this.nup = nup;
    }

    public String getPassport() {
        return passport;
    }


    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    /***********RELACIONES**************/

    @OneToMany( mappedBy = "primaryKey.postulant",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    public Set<PostulantCertification> getCertifications() {
        return certifications;
    }

    public void setCertifications(Set<PostulantCertification> certifications) {
        this.certifications = certifications;
    }

    @OneToMany( mappedBy = "postulant",
                cascade = CascadeType.ALL)
    public Set<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(Set<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    @OneToMany( mappedBy = "postulant",
                cascade = CascadeType.ALL)
    public Set<PostulantSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<PostulantSkill> skills) {
        this.skills = skills;
    }

    @OneToMany(mappedBy = "pk.postulant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(Set<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }

    @OneToMany(mappedBy = "pk.postulant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<AcademicExperience> getAcademicExperiences() {
        return academicExperiences;
    }

    public void setAcademicExperiences(Set<AcademicExperience> academicExperiences) {
        this.academicExperiences = academicExperiences;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "postulant", cascade = CascadeType.REMOVE)
    public Set<Award> getAwards() {
        return awards;
    }

    public void setAwards(Set<Award> awards) {
        this.awards = awards;
    }

    @OneToMany( mappedBy = "primaryKey.postulant",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public Set<PostulantPublication> getPostulantPublications() {
        return postulantPublications;
    }

    public void setPostulantPublications(Set<PostulantPublication> postulantPublications) {
        this.postulantPublications = postulantPublications;
    }

    @OneToMany(mappedBy = "primaryKey.postulant",
    cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<PostulantLanguage> getPostulantLanguages() {
        return postulantLanguages;
    }

    public void setPostulantLanguages(Set<PostulantLanguage> postulantLanguages) {
        this.postulantLanguages = postulantLanguages;
    }

    @Override
    public String toString() {
        return "Postulant{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", dui='" + dui + '\'' +
                ", birthday=" + birthday +
                ", nit='" + nit + '\'' +
                ", gender=" + gender +
                ", cellphone='" + cellphone + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", passport='" + passport + '\'' +
                ", nup='" + nup + '\'' +
                '}';
    }
}
