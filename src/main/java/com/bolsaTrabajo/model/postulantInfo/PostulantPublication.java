package com.bolsaTrabajo.model.postulantInfo;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.AcademicTitleCat;
import com.bolsaTrabajo.model.catalog.Publication;
import com.bolsaTrabajo.model.compositeKeys.PostulantPublicationId;
import com.bolsaTrabajo.service.PostulantPublicationService;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.service.PublicationService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import org.slf4j.Logger;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


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
    private PublicationService publicationService;

    @Autowired
    PostulantPublication postulantPublication;

    private PostulantPublicationId primaryKey = new PostulantPublicationId();

    private Date publicationDate;

    @Autowired
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


    public PostulantPublication save(String username, PostulantPublication postulantPublicationFromRequest){
        Postulant postulant = postulantService.findByUsername(username);
        Publication publication = publicationService.findById(postulantPublicationFromRequest.getPublication().getId());
        PostulantPublicationId primaryKey = new PostulantPublicationId(postulant,publication);

        PostulantPublication newPub = new PostulantPublication();
        newPub.setPostulant(primaryKey.getPostulant());
        newPub.setPublication(primaryKey.getPublication());
        newPub.setPublicationDate(postulantPublicationFromRequest.getPublicationDate());

        postulant.getPostulantPublications().add(newPub);
        postulantService.save(postulant);
        return newPub;
    }

    public boolean delete(String username,String code){

        Postulant postulant = postulantService.findByUsername(username);

        Publication publication = publicationService.findPublicationByCodigo(code);

        PostulantPublicationId postulantPublicationId = new PostulantPublicationId();

        postulantPublicationId.setPostulant(postulant);
        postulantPublicationId.setPublication(publication);

        PostulantPublication postulantPublication = postulantPublicationService.postulantPublication(postulantPublicationId);

        try{
            postulantPublicationService.delete(postulantPublication);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public String toString() {
        return "PostulantPublication{" +
                "primaryKey=" + primaryKey +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
