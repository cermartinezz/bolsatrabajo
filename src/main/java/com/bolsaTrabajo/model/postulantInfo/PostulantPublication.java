package com.bolsaTrabajo.model.postulantInfo;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.AcademicTitleCat;
import com.bolsaTrabajo.model.catalog.Publication;
import com.bolsaTrabajo.model.compositeKeys.PostulantPublicationId;
import com.bolsaTrabajo.service.PostulantPublicationService;
import com.bolsaTrabajo.service.PostulantService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import org.slf4j.Logger;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mvip on 05-28-17.
 */
@Entity
@Table(name="postulants_publications")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.postulant", joinColumns = @JoinColumn(name = "id")),
        @AssociationOverride(name = "primaryKey.publication", joinColumns = @JoinColumn(name = "publication_id"))
})
public class PostulantPublication implements Serializable{

    private Logger logger = LoggerFactory.getLogger(PostulantPublication.class);

    @Autowired
    private PostulantPublicationService postulantPublicationService;
    @Autowired
    private PostulantService postulantService;

    @Autowired
    PostulantPublication postulantPublication;

    private PostulantPublicationId primaryKey = new PostulantPublicationId();
    private Date publicationDate;


    public PostulantPublication(PostulantPublicationService postulantPublicationService, PostulantService postulantService) {
        this.postulantPublicationService = postulantPublicationService;
        this.postulantService = postulantService;
    }

    public PostulantPublication() {
    }

    @EmbeddedId
    public PostulantPublicationId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PostulantPublicationId primaryKey) {
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
    public Publication getPublication() {
        return getPrimaryKey().getPublication();
    }

    public void setPublication(Publication publication) {
        getPrimaryKey().setPublication(publication);
    }

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "publication_date")
    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }


    @Override
    public String toString() {
        return "PostulantPublication{" +
                "primaryKey=" + primaryKey +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
