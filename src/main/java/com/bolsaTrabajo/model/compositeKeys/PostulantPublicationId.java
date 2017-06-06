package com.bolsaTrabajo.model.compositeKeys;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.AcademicTitleCat;
import com.bolsaTrabajo.model.catalog.Publication;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by mvip on 06-02-17.
 */
@Embeddable
public class PostulantPublicationId implements Serializable{

    private Postulant postulant;
    private Publication publication;

    public PostulantPublicationId(Postulant postulant, Publication publication) {
        this.postulant = postulant;
        this.publication = publication;
    }

    public PostulantPublicationId() {
    }

    @ManyToOne
    @JsonIgnore
    public Postulant getPostulant() {
        return postulant;
    }

    public void setPostulant(Postulant postulant) {
        this.postulant = postulant;
    }

    @ManyToOne
    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    @Override
    public String toString() {
        return "PostulantPublicationId{" +
                "postulant=" + postulant +
                ", publication=" + publication +
                '}';
    }
}
