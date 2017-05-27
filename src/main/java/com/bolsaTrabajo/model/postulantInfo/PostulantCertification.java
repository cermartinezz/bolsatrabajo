package com.bolsaTrabajo.model.postulantInfo;

import com.bolsaTrabajo.model.catalog.Certification;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.compositeKeys.PostulantCertificationId;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="postulants_certifications")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.postulant", joinColumns = @JoinColumn(name = "id")),
        @AssociationOverride(name = "primaryKey.certification", joinColumns = @JoinColumn(name = "certification_id"))
})
public class PostulantCertification implements Serializable {

    private PostulantCertificationId primaryKey = new PostulantCertificationId();
    private Date expirationDate;
    private String certificationCode;

    @EmbeddedId
    public PostulantCertificationId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PostulantCertificationId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public Postulant getPostulant(){
        return getPrimaryKey().getPostulant();
    }

    public void setPostulant(Postulant postulant){
        getPrimaryKey().setPostulant(postulant);
    }

    @Transient
    public Certification getCertification(){
        return getPrimaryKey().getCertification();
    }

    public void setCertification(Certification certification){
        getPrimaryKey().setCertification(certification);
    }

    @Column(unique = true)
    public String getCertificationCode() {
        return certificationCode;
    }

    public void setCertificationCode(String certificationCode) {
        this.certificationCode = certificationCode;
    }

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    @Column(name = "expiration_date")
    public Date getExpirationDate() {
        return expirationDate;
    }


    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "PostulantCertification{" +
                "primaryKey=" + primaryKey +
                ", expirationDate=" + expirationDate +
                ", certificationCode='" + certificationCode + '\'' +
                '}';
    }
}

