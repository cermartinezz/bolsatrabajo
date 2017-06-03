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
    private String code;

    public PostulantPublicationId(Postulant postulant, Publication publication, String code) {
        this.postulant = postulant;
        this.publication = publication;
        this.code = code;
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

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
