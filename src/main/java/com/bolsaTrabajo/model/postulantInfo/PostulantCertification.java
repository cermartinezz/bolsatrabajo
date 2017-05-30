package com.bolsaTrabajo.model.postulantInfo;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.Certification;
import com.bolsaTrabajo.model.compositeKeys.PostulantCertificationId;
import com.bolsaTrabajo.service.PostulantCertificationService;
import com.bolsaTrabajo.service.PostulantService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private Logger logger = LoggerFactory.getLogger(PostulantCertification.class);

    @Autowired
    private PostulantCertificationService postulantCertificationService;
    @Autowired
    private PostulantService postulantService;

    @Autowired
    PostulantCertification postulantCertification;

    private PostulantCertificationId primaryKey = new PostulantCertificationId();
    private Date expirationDate;

    @Autowired
    public PostulantCertification(PostulantCertificationService postulantCertificationService,PostulantService postulantService) {
        this.postulantCertificationService = postulantCertificationService;
        this.postulantService = postulantService;
    }

    public PostulantCertification() {
    }

    @EmbeddedId
    public PostulantCertificationId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PostulantCertificationId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public String getCode(){

        return primaryKey.getCode();
    }

    @Transient
    public void setCode(String certificationCode){

        primaryKey.setCode(certificationCode);
    }

    @Transient
    @JsonIgnore
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
                '}';
    }

    public void delete(Postulant postulant,Certification certification,String code){

        PostulantCertificationId postulantCertificationId = new PostulantCertificationId(postulant,certification,code);

        PostulantCertification certificationOfPostulant = postulantCertificationService.getCertificationOfPostulant(postulantCertificationId);

        postulantCertificationService.delete(certificationOfPostulant);

    }

    public void update(Postulant postulant,Certification certification,String code,PostulantCertification freshCertification){


        this.postulantCertification.delete(postulant,certification,code);

        this.postulantCertification.create(postulant,certification,freshCertification);

    }

    public void create(Postulant postulant,Certification certification,PostulantCertification freshCertification){

        PostulantCertificationId newPrimaryKey = new PostulantCertificationId(postulant, certification, freshCertification.getCode());

        PostulantCertification postulantCertification  = new PostulantCertification();

        postulantCertification.setCertification(newPrimaryKey.getCertification());

        postulantCertification.setPostulant(newPrimaryKey.getPostulant());

        postulantCertification.setCode(newPrimaryKey.getCode());

        postulantCertification.setExpirationDate(freshCertification.getExpirationDate());

        postulant.getCertifications().add(postulantCertification);

        postulantService.save(postulant);
    }

    @Override
    public int hashCode() {
        return (getPrimaryKey() != null ? getPrimaryKey().hashCode() : 0);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(!(obj instanceof PostulantCertification))
            return false;

        PostulantCertification other = (PostulantCertification) obj;
        if(primaryKey == null){
            if(other.primaryKey != null)
                return false;
        }else if(!primaryKey.equals(other.primaryKey))
            return false;
        return true;
    }
}

